package com.example.turborotaryphone.controller;

import com.example.turborotaryphone.model.Message;
import com.example.turborotaryphone.model.User;
import com.example.turborotaryphone.model.dto.MessageDto;
import com.example.turborotaryphone.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String find(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model,
                       @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                       @AuthenticationPrincipal User user) {
        Page<MessageDto> page = messageService.messageList(pageable, filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException {
        message.setUser(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            messageService.saveFile(message, file);
            messageService.save(message);

            model.addAttribute("message", null);
        }

        return "redirect:/main";
    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User user,
                               @RequestParam(required = false) Message message,
                               Model model,
                               @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MessageDto> page = messageService.messageListForUser(pageable, currentUser, user);

        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("isCurrentUser", currentUser.getId().equals(user.getId()));
        model.addAttribute("url", "/user-messages/" + user.getId());

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessages(@AuthenticationPrincipal User currentUser,
                                 @PathVariable Long user,
                                 @RequestParam("id") Message message,
                                 @RequestParam("text") String text,
                                 @RequestParam("tag") String tag,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        if (message.getUser().getId().equals(currentUser.getId())) {
            if (text != null && !text.isEmpty()) {
                message.setText(text);
            }

            if (tag != null && !tag.isEmpty()) {
                message.setTag(tag);
            }

            messageService.saveFile(message, file);
            messageService.save(message);
        }

        return "redirect:/user-messages/" + user;
    }

    @GetMapping("/messages/{message}/like")
    public String like(@AuthenticationPrincipal User currentUser,
                       @PathVariable Message message,
                       RedirectAttributes redirectAttributes,
                       @RequestHeader(required = false) String referer) {
        Set<User> likes = message.getLikes();

        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }

}
