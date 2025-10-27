# Mini Projeto Kotlin - Sistema de Gerenciamento de Estoque

Sistema de gerenciamento de estoque desenvolvido em Kotlin para a disciplina de Desenvolvimento Mobile. O sistema processa compras, vendas, gerencia estoque e realiza buscas em produtos.

## Funcionalidades
- ✅ Processamento de arquivo de compras (`compras.csv`)
- ✅ Processamento de arquivo de vendas (`vendas.csv`) 
- ✅ Geração de relatórios de estoque
- ✅ Sistema de busca por produtos
- ✅ Geração de balancete financeiro

## Estrutura do Projeto
```
miniprojeto/
├── miniprojeto.jar                 # Arquivo executável
├── build.gradle.kts               # Configuração do Gradle
├── src/main/kotlin/org/example/
│   ├── Main.kt                    # Classe principal
│   ├── Product.kt                 # Classe base Product
│   ├── Clothing.kt                # Produtos do tipo Roupa
│   ├── Electronic.kt              # Produtos do tipo Eletrônico
│   ├── Collectible.kt             # Produtos do tipo Colecionável
│   └── services/
│       ├── PurchaseSaleService.kt # Serviço de compras e vendas
│       ├── StockManagementService.kt # Serviço de estoque
│       ├── BalanceService.kt      # Serviço de balancete
│       └── SearchService.kt       # Serviço de buscas
```

### Estrutura de Classes
- **Product**: Classe base abstrata para todos os produtos
- **Clothing**: Produtos de vestuário (camisas, moletons, acessórios)
- **Electronic**: Produtos eletrônicos (videogames, jogos, portáteis)
- **Collectible**: Itens colecionáveis (bonecos, livros, outros)

## Categorias de Produtos Suportadas
1. **ROUPA** - Vestuário e acessórios
2. **ELETRONICO** - Dispositivos eletrônicos
3. **COLECIONAVEL** - Itens colecionáveis


## Estrutura de Arquivos de Entrada

### Pasta de Entrada
```
pasta_entrada/
├── compras.csv    # Obrigatório - Lista de produtos comprados
├── vendas.csv     # Obrigatório - Lista de produtos vendidos  
└── busca.csv      # Opcional - Critérios de busca
```

### Arquivo compras.csv
Formato esperado:
```
ID,Quantidade,Nome,PreçoCompra,PreçoVenda,Categoria,Tipo,Tamanho,CorPrimaria,CorSecundaria,Versao,AnoFabricacao,Material,Relevancia
```

### Arquivo vendas.csv
Formato esperado:
```
CodigoProduto,QuantidadeVendida
```

### Arquivo busca.csv
Formato esperado:
```
Categoria,Tipo,Tamanho,Cor primaria,Cor secundario,Versão,Ano de fabricação,Material de fabricação,Relevância
```

## Saída Gerada

### Pasta de Saída
```
pasta_saida/
├── estoque_geral.csv           # Estoque geral de produtos
├── estoque_por_categoria.csv   # Estoque agrupado por categoria
├── balancete.csv               # Relatório financeiro
└── resultado_busca.csv         # Resultados das buscas (se houver busca.csv)
```

## Desenvolvimento

### Compilação e Build
```bash
# Gerar JAR
./gradlew jar

# O JAR será gerado na raiz do projeto como miniprojeto.jar
```
## Como Executar

### Pré-requisitos
- Java 17 ou superior
- Gradle (opcional, para desenvolvimento)

### Execução do JAR
```bash
java -jar miniprojeto.jar <pasta_entrada> <pasta_saida>
```

### Exemplo
```bash
java -jar miniprojeto.jar /caminho/entrada /caminho/saida
```

## Autor
Elder Storck - Disciplina de Desenvolvimento Mobile
