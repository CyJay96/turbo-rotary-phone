<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Add new user

    <div>
        ${message!""}
    </div>

    <@l.login "/registration" />
</@c.page>
