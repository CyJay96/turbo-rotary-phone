<#import "parts/common.ftlh" as c>

<@c.page>
    <form class="row gx-3 gy-2 align-items-center" action="/main" method="get">
        <div class="col-sm-2">
            <input class="form-control" type="text" name="filter" value="${filter?if_exists}" placeholder="Search by tag">
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <#include "parts/messageEdit.ftlh" />

    <#include "parts/messageList.ftlh" />
</@c.page>
