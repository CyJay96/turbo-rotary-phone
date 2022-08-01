<a class="btn btn-primary mt-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Message editor
</a>

<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="mt-3">
        <form action="/main" method="post" enctype="multipart/form-data">
            <div class="mt-3">
                <input class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>" type="text" name="text" placeholder="Enter a message"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="mt-3">
                <input class="form-control"
                       value="<#if message??>${message.tag}</#if>" type="text" name="tag" placeholder="Enter a tag"/>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="mt-3">
                <input class="form-control" type="file" name="file"/>
            </div>
            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
            </div>
            <div class="mt-3">
                <button type="submit" class="btn btn-primary">Save message</button>
            </div>
        </form>
    </div>
</div>
