<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Login page
    <@l.login "/login" />
    <button>
        <a href="/registration" type="button">Sign up</a>
    </button>
</@c.page>
