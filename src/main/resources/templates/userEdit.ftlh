<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>User Editor</h5>

    <div class="mt-3">
        <form action="/user" method="post">
            <div class="row mb-3">
                <label class="col-sm-2 col-form-label">User Name:</label>
                <div class="col-sm-6">
                    <input type="text" name="username" class="form-control" value="${user.username}" placeholder="User name"/>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password" class="form-control" placeholder="Password"/>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control"
                           value="${user.email!''}" placeholder="email@example.com"/>
                </div>
            </div>

            <div class="mt-3">
                <#list roles as role>
                    <div class="form-check">
                        <input class="form-check-input" id="gridCheck1" type="checkbox"
                               name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>
                        <label class="form-check-label" for="gridCheck1">
                            ${role}
                        </label>
                    </div>
                </#list>
            </div>

            <div class="mt-3">
                <input type="hidden" value="${user.id}" name="userId"/>
            </div>

            <div class="mt-3">
                <input type="hidden" value="${_csrf.token}" name="_csrf"/>
            </div>

            <div class="mt-3">
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form>
    </div>
</@c.page>
