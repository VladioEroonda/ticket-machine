<#import "parts/page-template.ftlh" as p>
<#import "/spring.ftl" as spring/>

<@p.template>


    <form action="/ticket-submit" method="POST">
        Subj:
<#--        <@spring.bind "testr.id"/>-->
<#--        <input type="text"/>-->

        <@spring.formInput "ticket.subject"/>
        <@spring.formInput "ticket.message.messageText"/>

        <@spring.showErrors "<br>"/>
<#--        <@spring.bind "testr.name"/>-->
<#--        <input type="text"/>-->
<#--        <input type="text"-->
<#--               name="${spring.status.expression}"-->
<#--               value="${spring.status.value?default("")}"/><br>-->
<#--        <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>-->

        <br>
        <input type="submit" value="submit"/>
    </form>

<#--    <h2>Register ur Ticket:</h2>-->
<#--    ${ticket.subject}-->
<#--    <form action="/ticket-submit" method="post">-->
<#--        <p>Name: </p><input type="text" placeholder="f" name="dd" required><br>-->
<#--&lt;#&ndash;        <p>Surname: </p><input type="text" placeholder="" name="${ticket.getMessage().messageText}" required><br>&ndash;&gt;-->
<#--        <button type="submit">Register</button>-->
<#--    </form>-->

</@p.template>