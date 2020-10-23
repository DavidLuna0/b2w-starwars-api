<h1 align="center">  B2W Star Wars Api </h1>

<p align="center">
  API desenvolvida devido ao desafio da B2W para cadastro de planetas da consagrada franquia Star Wars.
  <br/>
  Neste projeto também se faz uso da API externa https://swapi.dev/
</p>

## Tecnologias Utilizadas

 * Java 1.8
 * SpringBoot 2.3.4.RELEASE
 * MongoDb
 * Springfox Swagger 2.7.0

 Link para a documentação da APIa com swagger: http://localhost:8081/swagger-ui.html

</br>

## Primeiros Passos

<p>
  Primeiramente é necessário fazer o download do código da aplicação e buildar com o maven para que seja gerado o arquivo <i>b2w-starwars-api-1.0.0</i> no diretorio <b>target</b>.</br>
  Após o build devemos subir o banco mongo usando docker com os comandos abaixo.
</p>

```
docker pull mongo

docker run -d -p 27017:27017 -e AUTH=no mongo
```
<p>
  Em seguida, criaremos a imagem docker da aplicação com o .JAR gerado após o build e rodaremos ela.
</p>

```
docker build -t starwars-api-1.0.0 .

docker run -d -p 8081:8081 starwars-api-1.0.0
```
 
## Features
 - Inserir Planeta
 - Listar planetas
 - Buscar por nome
 - Buscar por id
 - Deletar planeta
 
 ## Como utilizar


### Cadastrando planeta na base mongo

**POST** `http://localhost:8081/api/planets`

```json

// request
{
  "name": "Endor",
  "climate": "temperate",
  "terrain": "forests, mountains, lakes"
}
```
```json
// response
{
  "id": "5f9318141e13d7090b7454fd",
  "name": "Endor",
  "climate": "temperate",
  "terrain": "forests, mountains, lakes",
  "numberOfFilms": 1
}

```

### Listando planetas cadastrados na base mongo

**GET** `http://localhost:8081/api/planets`

```json

// response
[
  {
    "id": "5f9311a21e13d7090b7454fc",
    "name": "Tatooine",
    "climate": "arid",
    "terrain": "desert",
    "numberOfFilms": 5
  },
  {
    "id": "5f9318141e13d7090b7454fd",
    "name": "Endor",
    "climate": "temperate",
    "terrain": "forests, mountains, lakes",
    "numberOfFilms": 1
  }
]
```
### Buscando planeta pelo nome na base mongo

**GET** `http://localhost:8081/api/planets/?name=`**[name]** </br></br>
`http://localhost:8081/api/planets/?name=Endor`
```json

// response 
{
  "id": "5f9318141e13d7090b7454fd",
  "name": "Endor",
  "climate": "temperate",
  "terrain": "forests, mountains, lakes",
  "numberOfFilms": 1
}
```

### Buscando planeta pelo Id na base Mongo

**GET** `http://localhost:8081/api/planets`**[ID]** </br></br>
`http://localhost:8081/api/planets/5f930e861e13d7090b7454fb`
```json

// response 
{
  "id": "5f9318141e13d7090b7454fd",
  "name": "Endor",
  "climate": "temperate",
  "terrain": "forests, mountains, lakes",
  "numberOfFilms": 1
}
```

### Deletando planeta da base mongo pelo Id

**DELETE** `http://localhost:8081/api/planets`**[ID]** </br></br>
`http://localhost:8081/api/planets/5f930e861e13d7090b7454fb`
```json

// response 
//204 No Content
//No body returned for response
```
