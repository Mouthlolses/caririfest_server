# =========================
# STAGE 1 — BUILD
# =========================
FROM gradle:8.6-jdk21 AS builder

WORKDIR /app

# Copia arquivos que mudam menos (melhora MUITO o cache)
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Baixa dependências antes (cache layer)
RUN gradle dependencies --no-daemon || true

# Agora copia o restante do projeto
COPY . .

# Gera o fat jar do Spring
RUN gradle bootJar --no-daemon


# =========================
# STAGE 2 — RUNTIME
# =========================
# Jammy é mais estável que Alpine para JVM
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia qualquer jar gerado (não depende de SNAPSHOT)
COPY --from=builder /app/build/libs/*.jar app.jar


# 🔐 NÃO rodar como root (boa prática real de produção)
RUN useradd -m springuser
USER springuser


# Porta padrão (quem manda é o Spring via ENV)
EXPOSE 8080

# JVM já entende container — não precisa mais UseContainerSupport no Java 21
ENTRYPOINT ["java","-jar","app.jar"]