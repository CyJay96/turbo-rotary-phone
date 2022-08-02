<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page />

<div class="row row-cols-1 row-cols-md-3 g-4" id="message-list">
    <#list page.content as message>
        <div class="col">
            <div class="card m-3" data-id="${message.id}">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top" alt="${message.id}"/>
                </#if>

                <div class="m-2">
                    <span>${message.text}</span><br/>
                    <i>#${message.tag}</i>
                </div>

                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-center" href="/user-messages/${message.user.id}">${message.userName}</a>
                        <a class="col align-self-center" href="/messages/${message.id}/like">
                            <#if message.meLiked>
                                <i class="fas fa-heart"></i>
                            <#else>
                                <i class="far fa-heart"></i>
                            </#if>

                            <#if message.likes != 0>
                                ${message.likes}
                            </#if>
                        </a>
                        <#if message.user.id == currentUserId>
                            <a href="/user-messages/${message.user.id}?message=${message.id}"
                               class="col btn btn-outline-primary">
                                Edit
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        <div class="mt-10">No messages</div>
    </#list>
</div>

<@p.pager url page />
