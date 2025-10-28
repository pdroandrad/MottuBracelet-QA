# Como Executar a Aplicação MottuBracelet no VSCode

## Pré-requisitos

### 1. Instalar Java 21
- Baixe e instale o Java 21 JDK
- Verifique a instalação: `java -version`

### 2. Instalar Maven
- Baixe e instale o Apache Maven
- Verifique a instalação: `mvn -version`

### 3. Instalar Oracle Database (ou usar H2 para desenvolvimento)
- Para desenvolvimento local, você pode usar H2 (já configurado como alternativa)
- Para produção, configure o Oracle Database

### 4. Extensões do VSCode
Instale as seguintes extensões no VSCode:
- **Extension Pack for Java** (Microsoft)
- **Spring Boot Extension Pack** (VMware)
- **Thunder Client** (para testar APIs)

## Configuração do Banco de Dados

### Opção 1: Usar H2 Database (Recomendado para desenvolvimento)
1. Abra o arquivo `src/main/resources/application-dev.properties`
2. A configuração H2 já está pronta para uso

### Opção 2: Usar Oracle Database
1. Configure o Oracle Database
2. Edite o arquivo `src/main/resources/application.properties`
3. Ajuste as credenciais de conexão

## Passos para Executar

### 1. Abrir o Projeto
```bash
cd MottuBracelet
code .
```

### 2. Configurar o Profile de Desenvolvimento
- O projeto está configurado para usar H2 em desenvolvimento
- As migrações Flyway serão executadas automaticamente

### 3. Executar a Aplicação

#### Opção A: Via VSCode (Recomendado)
1. Abra o arquivo `MottuBraceletApplication.java`
2. Clique no botão "Run" que aparece acima da classe main
3. Ou use `Ctrl+F5` para executar sem debug

#### Opção B: Via Terminal Integrado
```bash
# No terminal do VSCode
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

#### Opção C: Via Maven
```bash
mvn clean spring-boot:run -Dspring.profiles.active=dev
```

### 4. Acessar a Aplicação
- URL: http://localhost:8080
- Será redirecionado para a página de login

## Usuários de Teste

### Administrador
- **Username:** admin
- **Senha:** admin123
- **Permissões:** Acesso total ao sistema

### Operador
- **Username:** operador
- **Senha:** operador123
- **Permissões:** Criar, editar pátios, motos e dispositivos

### Visualizador
- **Username:** viewer
- **Senha:** viewer123
- **Permissões:** Apenas visualização

## Estrutura de URLs

- `/` - Redireciona para login
- `/login` - Página de login
- `/dashboard` - Dashboard principal
- `/patios` - Gerenciar pátios
- `/motos` - Gerenciar motos
- `/dispositivos` - Gerenciar dispositivos
- `/historicos` - Visualizar histórico
- `/admin/usuarios` - Gerenciar usuários (apenas ADMIN)

## Console H2 (Desenvolvimento)
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (deixar em branco)

## Troubleshooting

### Erro de Porta em Uso
```bash
# Verificar processo na porta 8080
netstat -ano | findstr :8080
# Matar processo (Windows)
taskkill /PID <PID> /F
```

### Erro de Java Version
- Verifique se o Java 21 está instalado
- Configure a variável JAVA_HOME
- No VSCode: `Ctrl+Shift+P` > "Java: Configure Runtime"

### Erro de Maven
- Verifique se o Maven está no PATH
- Use o wrapper: `./mvnw` (Linux/Mac) ou `mvnw.cmd` (Windows)

### Problemas de Dependências
```bash
# Limpar e reinstalar dependências
./mvnw clean install
```

## Desenvolvimento

### Hot Reload
- O Spring Boot DevTools está configurado
- Mudanças em templates Thymeleaf são recarregadas automaticamente
- Para mudanças em Java, use `Ctrl+Shift+F5` para restart

### Debug
1. Coloque breakpoints no código
2. Use `F5` para executar em modo debug
3. Use o painel de debug do VSCode

### Logs
- Os logs aparecem no terminal integrado
- Nível de log configurado para DEBUG em desenvolvimento

## Estrutura do Projeto

```
MottuBracelet/
├── src/main/java/
│   └── br/com/fiap/MottuBracelet/
│       ├── config/          # Configurações (Security, etc.)
│       ├── controller/      # Controllers REST e Web
│       ├── dto/            # Data Transfer Objects
│       ├── exception/      # Tratamento de exceções
│       ├── model/          # Entidades JPA
│       ├── repository/     # Repositórios Spring Data
│       ├── service/        # Lógica de negócio
│       └── util/           # Utilitários e enums
├── src/main/resources/
│   ├── db/migration/       # Scripts Flyway
│   ├── static/            # CSS, JS, imagens
│   ├── templates/         # Templates Thymeleaf
│   └── application*.properties
└── src/test/              # Testes unitários
```