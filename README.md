# CashBack-Rabbit

# Add Customer

Post: ``localhost:8081/customer/create``

``{
    "name": "CashBack",
    "queues": [
        {
            "domainId": "cashback",
            "version": "v1",
            "nameQueue": "first",
            "type": "IN"
        },
        {
            "domainId": "cashback",
            "version": "v2",
            "nameQueue": "last",
            "type": "OUT"
        }
    ]
}``


# Add Queue
Get: ``localhost:8081/message/create``

# Delete all Queue
Delete: ``localhost:8081/message/delete``

# Delete Queue by name in broker
Delete: ``localhost:8081/message/broker/cashback.v2.last``

# Sending Message
Post: ``localhost:8081/message/create-message``

``{
    "message": "Mensagem enviada com sucesso!!",
    "type": "OUT"
}``

