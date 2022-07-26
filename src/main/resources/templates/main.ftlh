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

    <a class="btn btn-primary mt-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>

    <div class="collapse" id="collapseExample">
        <div class="mt-3">
            <form action="/main" method="post" enctype="multipart/form-data">
                <div class="mt-3">
                    <input class="form-control" type="text" name="text" placeholder="Enter a message">
                </div>
                <div class="mt-3">
                    <input class="form-control" type="text" name="tag" placeholder="Enter a tag">
                </div>
                <div class="mt-3">
                    <input class="form-control" type="file" name="file">
                </div>
                <div class="mt-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </div>
                <div class="mt-3">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list messages as message>
            <div class="col">
                <div class="card m-3">
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top" alt="${message.id}">
                    </#if>

                    <div class="m-2">
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>

                    <div class="card-footer text-muted">
                        ${message.userName}
                    </div>
                </div>
            </div>
        <#else>
            <div class="mt-10">No messages</div>
        </#list>
    </div>

</@c.page>
