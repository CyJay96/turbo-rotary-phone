<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>

    <div>
        <form action="/main" method="post">
            <input type="text" name="text" placeholder="Enter a message">
            <input type="text" name="tag" placeholder="Enter a tag">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Add</button>
        </form>
    </div>

    <div>Filter of messages</div>
    <form action="/main" method="get">
        <input type="text" name="filter" placeholder="Enter a tag">
        <button type="submit">Find</button>
    </form>

    <div>List of messages</div>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.userName}</strong>
        </div>

    <#else>
        No messages
    </#list>

</@c.page>
