<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h5>Add new user</h5>

    <div>
        ${message?if_exists}
    </div>

    <@l.login "/registration" true />
</@c.page>
