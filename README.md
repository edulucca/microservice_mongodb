# 🌐 Catálogo de Sites - Arquitetura de Microsserviços

Este é um projeto de estudo e portfólio focado na construção de uma arquitetura de microsserviços escalável e resiliente, utilizando o ecossistema Spring Cloud e persistência de dados em banco NoSQL.

A aplicação consiste em um catálogo de sites, onde é possível cadastrar, listar, buscar e remover registros. Toda a comunicação externa é centralizada em um API Gateway, que roteia as requisições dinamicamente através de um Service Discovery.

## 🏗️ Arquitetura do Projeto

O ecossistema foi desenvolvido no modelo **Monorepo** e é composto por três componentes principais:

1. **`eureka-server` (Porta 8761):** Atua como o Service Registry (Netflix Eureka). É a "lista telefônica" da rede, onde todos os microsserviços se registram ao inicializar.
2. **`api-gateway` (Porta 8081):** A porta de entrada pública da aplicação (Spring Cloud Gateway). Recebe as requisições do cliente e faz o roteamento dinâmico e balanceamento de carga (Load Balancer) para os serviços internos.
3. **`site-catalogo-service` (Porta 8080):** O microsserviço de domínio responsável por gerenciar a regra de negócio do catálogo e realizar a comunicação direta com o banco de dados.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework Principal:** Spring Boot 3.5.x
* **Nuvem e Roteamento:** Spring Cloud (Gateway, Netflix Eureka Client/Server, LoadBalancer)
* **Banco de Dados:** MongoDB Atlas (DBaaS)
* **Mapeamento Objeto-Documento:** Spring Data MongoDB
* **Validações:** Spring Boot Starter Validation
* **Utilitários:** Lombok
* **Gerenciador de Dependências:** Maven

## 🛠️ Como Executar Localmente

### Pré-requisitos
* Java 17 ou superior instalado.
* Maven instalado (ou utilizar o *wrapper* da IDE).
* Conta no MongoDB Atlas com um cluster configurado.

### 1. Configuração das Variáveis de Ambiente
Para rodar o `site-catalogo-service`, você precisa configurar as credenciais do MongoDB. Crie um arquivo chamado `.env.properties` na raiz da pasta `site-catalogo-service` com o seguinte formato:

```properties
MONGO_USER=seu_usuario
MONGO_PASSWORD=sua_senha
MONGO_CLUSTER=seu_cluster.mongodb.net
MONGO_DB=nome_do_banco
```
*(Nota: Este arquivo está ignorado pelo `.gitignore` para garantir a segurança das credenciais).*

### 2. Ordem de Inicialização
Para que o registro de serviços funcione perfeitamente, inicie os projetos na sua IDE rigorosamente nesta ordem:

1. Inicie o **`eureka-server`** (Aguarde inicializar).
2. Inicie o **`site-catalogo-service`** (Aguarde registrar no Eureka).
3. Inicie o **`api-gateway`**.

Para verificar se tudo subiu corretamente, acesse o painel do Eureka no navegador: `http://localhost:8761`.

## 📌 Endpoints da API (via Gateway)

Todas as requisições devem ser feitas apontando para a porta do API Gateway (`8081`). O header `Content-Type: application/json` é obrigatório para os métodos POST e PUT.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/sites` | Cadastra um novo site. |
| `GET` | `/api/sites` | Lista todos os sites cadastrados. |
| `GET` | `/api/sites/busca?nome={termo}` | Busca sites por nome (Case Insensitive). |
| `PUT` | `/api/sites/{id}` | Atualiza os dados de um site existente. |
| `DELETE` | `/api/sites/{id}` | Remove um site do catálogo. |

### Exemplo de Payload (POST/PUT)
```json
{
  "nome": "Google Brasil",
  "endereco": "[https://www.google.com.br](https://www.google.com.br)"
}
```

## 👨‍💻 Autor

**Eduardo Santana** Analista de Sistemas Pleno | Estudante de Ciência da Computação na UnB
[LinkedIn](https://www.linkedin.com/in/seu-perfil) | [GitHub](https://github.com/seu-usuario)