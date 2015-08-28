# QConRio2015

[Pt-Br] Abrir e rodar o projeto:
 * git clone git@github.com:JacAbreu/QConRio2015.git
 * mvn clean compile exec:java

[Pt-Br] Passos para criar sua linguagem utilizando ANTL4 e Intellij:
 * Criar projeto baseado no maven.
 * Adicionar o pom.xml contido neste exemplo, por exemplo :).
 * Adicionar o plugin do ANTLR4 (no caso do Intellij é em Settings do projeto -> plugins -> ANTLR 4 Grammar Plugin)
 * Criar um diretório (mesmo nível de onde estarão os java sources) chamado antlr
 * Dentro dele, criar um arquivo nome_da_sua_linguagem.g4 - nesse arquivo será definida a sua gramática

 Gerar os arquivos: mvn clean compile

 Os arquivos abaixo serão gerados dentro de target->generated-sources -> antlr4:
 * Nome_da_sua_GramaticaLexer - fará a analise léxica, separando a entrada em tokens
 * Nome_da_sua_GramaticaParser - fará a análise sintática.
 * Nome_da_sua_GramaticaParser.StartContext tree = parser.start(); -> parser é Nome_da_sua_GramaticaParser instanciado. O método start chama o start definido na gramática (contida no nome_da_sua_linguagem.g4).

 Agora está tudo pronto para a implementação da(s) sua(s) classe(s) responsável(eis) pelos visitors. Neste exemplo, a classe que implementa os visitors da linguagem é a Evaluator.


