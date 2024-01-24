package com.ocsoares.awsemailsendingmicroservice.application.usecases.response;

// Mudei os Parâmetros do User para o Email só para PARAR de dar Erro
// OBS: Mas ainda NÃO SEI o EmailResponse q vou por...
public record EmailResponse(String to) {
}
