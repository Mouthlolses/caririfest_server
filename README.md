- Explicação Geral do Sistema


- Fluxo (Compra do Ingresso)

1. Usuário escolhe o evento e o ingresso

→ ainda não cria cliente

2. Vai para o checkout e preenche dados

→ nome, email, whatsapp

3. Nesse momento você cria o Client no banco

→ Você não quer conta de usuário →
então o Client é apenas um registro de comprador, criado somente no momento da compra.

→ clientService.createClient(request)

4. Cria a compra (Order / Ticket)

→ Relaciona o ingressos ao Client.

5. Finaliza pagamento -> Envia para a API(Gateway) -> Retorna resposta ->  

   PENDING,
   APPROVED,
   FAILED,
   CANCELED 

6. Confirmação recebida -> Expõe via App ao Cliente, envia ingressos via e-mail e whatsapp.