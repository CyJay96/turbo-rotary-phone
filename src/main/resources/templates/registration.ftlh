<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Add new user

    <div>
        ${message?if_exists}
    </div>

    <@l.login "/registration" />
</@c.page>
