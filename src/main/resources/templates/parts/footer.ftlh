<#include "security.ftlh">
<#import "login.ftlh" as l>

<div class="container">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
        <p class="col-md-4 mb-0 text-muted">© 2022 All rights reserved</p>

        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item">
                <a class="nav-link px-2 text-muted" aria-current="page" href="/">Home</a>
            </li>

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link px-2 text-muted" aria-current="page" href="/main">Messages</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link px-2 text-muted" aria-current="page" href="/user-messages/${currentUserId}">My messages</a>
                </li>
            </#if>

            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link px-2 text-muted" aria-current="page" href="/user">User list</a>
                </li>
            </#if>

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link px-2 text-muted" aria-current="page" href="/user/profile">Profile</a>
                </li>
            </#if>
        </ul>
    </footer>
</div>
