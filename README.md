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

 Link para a documentação da API com swagger: http://localhost:8081/swagger-ui.html

</br>

## Primeiros Passos

<p>
  Para fazer a aplicação e o banco de dados funcionarem basta baixar o código, entrar na pasta raiz do projeto e executar comando do docker-compose abaixo.
</p>

```
docker-compose up
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
