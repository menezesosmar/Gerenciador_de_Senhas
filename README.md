# 🔐 Gerenciador de Senhas

*Sistema de Gerenciamento de Senhas desenvolvido em Java com foco em segurança de dados, criptografia AES, autenticação de dois fatores (2FA), geração de senhas seguras e verificação de vazamentos com a API *Have I Been Pwned*.*

> 📌 Projeto acadêmico com implementação em linha de comando (sem interface gráfica), utilizando apenas bibliotecas Java e bibliotecas externas para autenticação 2FA e QR Code.

---

## 👨‍💻 Autor

**Osmar \[Menezes]** | Analista de Dados Financeiros<br/>
📚 Estudante de Análise e Desenvolvimento de Sistemas<br/>
🔗 GitHub: [github.com/SeuUsuario](https://github.com/menezesosmar)

---

## ⚙️ Tecnologias Utilizadas

* Java 17+
* AES (criptografia simétrica)
* Google Authenticator API (2FA via TOTP)
* ZXing (geração de QR Code)
* API Have I Been Pwned (verificação de vazamentos)
* Maven (gerenciador de dependências)
* IDE recomendada: IntelliJ IDEA ou Eclipse

---

## 📁 Estrutura de Pastas

```
Gerenciador-de-senhas-main/
├── README.md                     # Arquivo de documentação
├── QRCode.png                    # QR gerado para 2FA
├── /demo/
│   ├── pom.xml                   # Arquivo de configuração Maven
│   └── src/
│       └── main/java/
│           └── gerenciador_senhas/
│               ├── Main.java                   # Classe principal
│               ├── Credential.java             # Classe de modelo de credenciais
│               ├── CredentialManager.java      # Cadastro e listagem de credenciais
│               ├── PasswordEncryptor.java      # Criptografia AES
│               ├── PasswordGenerator.java      # Geração de senhas fortes
│               ├── LeakChecker.java            # Verificação de vazamentos com HIBP
│               └── Authenticator.java          # Autenticação 2FA (TOTP)
```

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

* Java 17 ou superior
* Maven instalado
* IDE com suporte a projetos Maven (Eclipse, IntelliJ, VSCode)

---

### 🔨 Passo a Passo

1️⃣ **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/Gerenciador-de-senhas.git
```

2️⃣ **Importe na IDE (Eclipse ou IntelliJ)**
Abra como projeto Maven.

3️⃣ **Instale as dependências (caso necessário)**

No terminal:

```bash
cd demo
mvn clean install
```

4️⃣ **Execute a aplicação**

Rode a classe `Main.java` como aplicação Java.

---

## 🛡️ Funcionalidades de Segurança

| Funcionalidade                | Implementado? | Detalhes Técnicos                                                           |
| ----------------------------- | ------------- | --------------------------------------------------------------------------- |
| **Criptografia AES**          | ✅             | Senhas de serviços criptografadas com AES de 128 bits                       |
| **Autenticação 2FA (TOTP)**   | ✅             | Geração de QR Code e autenticação com Google Authenticator                  |
| **Geração de senhas fortes**  | ✅             | Letras, números e símbolos aleatórios                                       |
| **Verificação de vazamentos** | ✅             | Uso da API Have I Been Pwned com SHA-1 e k-anonymity                        |
| **Armazenamento seguro**      | ⚠️            | As credenciais são mantidas em memória (sem persistência em banco de dados) |

---

## 📝 Observações

* O projeto **não utiliza interface gráfica**, sendo todo em linha de comando.
* A chave de criptografia AES está embutida no código (não recomendado para produção).
* QR Code gerado pode ser escaneado com apps como o Google Authenticator.
* Para persistência em banco de dados, recomenda-se integração futura com SQLite ou H2.

---

## 🧪 Exemplos de Uso

* Gerar uma senha forte:

  ```
  Password: G7@f!Bz91K^w
  ```

* Adicionar uma credencial:

  ```
  Serviço: Gmail
  Login: usuario@gmail.com
  Senha: (criptografada internamente)
  ```

* Verificação de vazamento:

  ```
  Senha encontrada em 7 vazamentos!
  ```

---

## 👨‍🏫 Requisitos Atendidos

✅ Cadastro de credenciais<br/>
✅ Criptografia AES<br/>
✅ Autenticação 2FA com TOTP<br/>
✅ Geração de senhas seguras<br/>
✅ Verificação de vazamento via API externa<br/>

---

Se quiser, posso salvar esse conteúdo como um arquivo `README.md` dentro da pasta do projeto. Deseja isso?
