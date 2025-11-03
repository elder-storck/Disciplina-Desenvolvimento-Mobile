# Mini App: Calculadora de Imposto

Este mini app calcula o imposto de renda mensal baseado em três fatores:

1. **Salário mensal**  
2. **Número de dependentes**  
3. **Gastos com saúde e educação**  

## Funcionalidades

- Recebe os valores do usuário via campos de input
- Calcula o **salário tributável** subtraindo dependentes e despesas permitidas
- Aplica a **alíquota** correta e a **dedução**
- Mostra o valor do imposto com **duas casas decimais**
- Mensagem de sucesso ao calcular

## Tecnologias usadas

- Kotlin
- Android Studio
- View Binding

## Como usar

1. Abra o projeto no Android Studio
2. Execute o app em um emulador ou dispositivo Android
3. Preencha os campos:
   - Salário mensal
   - Número de dependentes (opcional, se vazio assume 0)
   - Gastos com saúde e educação (opcional, se vazio assume 0)
4. Clique em **CALCULAR**
5. O resultado aparecerá abaixo do botão

## Observações

- Caso algum campo seja deixado em branco, o app assume **0** para dependentes ou gastos.
- O valor do imposto é exibido com **duas casas decimais**.
