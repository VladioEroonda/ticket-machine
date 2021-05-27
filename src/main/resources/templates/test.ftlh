<#import "parts/page-template.ftlh" as p>

<@p.template>

    <p align="center">Hello, ${user.name}</p>


        <#list ticketList as ticket>
            <li> ticket id: ${ticket.id}
            <li> text: ${ticket.getMessage().messageText}
        <#else>
            <strong>You have no Tickets yet..</strong>
        </#list>


</@p.template>