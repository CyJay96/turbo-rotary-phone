<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>User editor</h5>

    <div class="mt-3">
        <form action="/user" method="post">
            <div class="col-sm-2 mt-3">
                <input class="form-control" type="text" name="username" value="${user.username}">
            </div>
            <div class="mt-3">
                <#list roles as role>
                    <div class="form-check">
                        <input class="form-check-input" id="gridCheck1" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                        <label class="form-check-label" for="gridCheck1">
                            ${role}
                        </label>
                    </div>
                </#list>
            </div>
            <div class="mt-3">
                <input type="hidden" value="${user.id}" name="userId">
            </div>
            <div class="mt-3">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </div>
            <div class="mt-3">
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form>
    </div>
</@c.page>
