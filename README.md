# JumpPad

Este plugin permite que você crie "JumpPads" no seu servidor Minecraft, onde os jogadores podem ser impulsionados para cima ou na direção de sua visão ao pisar em um bloco configurado. É possível personalizar a força do impulso e a direção do movimento.

## Funcionalidades

- **Jumppads personalizáveis:** Defina a força e a direção (para cima ou na direção da visão do jogador).
- **Som ativado:** Toca um som personalizado para os jogadores próximos quando eles ativam o JumpPad.
- **Comando de configuração:** Use o comando `/jp` para configurar a força e a direção dos JumpPads.

## Requisitos
<p>
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle">
</p>

- **Spigot 1.8.8** ou superior.
- **Java 8+**.

## Configuração

### Arquivos de Configuração:

1. **config.yml**: Arquivo principal de configuração.
    - **sound.activate**: Ativa ou desativa o som ao ativar o JumpPad.
    - **sound.som**: O nome do som que será tocado para os jogadores próximos ao JumpPad. Exemplo: `ENTITY_PLAYER_LEVELUP`.

   Exemplo de **config.yml**:
   ```yaml
   sound:
     activate: true
     som: ENTITY_PLAYER_LEVELUP

## 📫 Comunidade

[![Discord](https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/VzDnWtcmNn)
