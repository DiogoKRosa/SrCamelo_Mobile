# 📱 SRCamelo Mobile

## 🧾 Sobre esse projeto

O **SRCamelo** é um aplicativo Android desenvolvido em **Kotlin** utilizando **Jetpack Compose**.  
O objetivo é conectar **clientes** e **vendedores ambulantes**, oferecendo funcionalidades para:

- 🧍‍♂️ Cadastro de clientes e vendedores ambulantes  
- 🛒 Cadastro, listagem e exibição de produtos  
- 💰 Realização de compras (fluxo básico de checkout)  
- 📍 Visualização da localização do vendedor ambulante (mapa / geolocalização)

> 🔗 **Observação:** o backend do projeto está em um repositório separado. Atualize o campo `API_URL` conforme o endereço do backend em desenvolvimento/produção.

---

## 🛠️ Ferramentas de desenvolvimento

- **Linguagem:** Kotlin  
- **UI Toolkit:** Jetpack Compose  
- **IDE:** Android Studio
- **Injeção de dependência:** Hilt (opcional, recomendado)  
- **Consumo de API:** Retrofit  
- **Navegação:** Navigation Compose  
- **Mapas / Geolocalização:** Google Maps API

---

## ▶️ Como rodar o projeto

### 1. Pré-requisitos
- Android Studio instalado 
- SDK Android e emulador configurados ou dispositivo físico conectado  
- Backend rodando (repositório separado)

### 2. Clone o repositório
```bash
git clone https://github.com/DiogoKRosa/SrCamelo_API.git
cd srcamelo-app
```

### 3. Adicionar arquivo com as chaves do projeto
 - Crie um arquivo com nome 'apikeys.properties' na raiz do projeto
 - Dentro do novo arquivo adicione as seguintes chaves:
 ```bash
 MAPS_API_KEY = (Inclua a chave do goggle maps api aqui)
 API_URL = "https://seu-backend.example.com/api"
 ```

 >🔗 Se o backend estiver em um repositório privado, certifique-se de rodá-lo localmente e apontar API_URL para http://10.0.2.2:porta/ (emulador Android) ou para o endereço correto do dispositivo.

 ### 4. Abra no Android Studio
 - File > Open... → selecione a pasta do projeto
 - Aguarde a sincronização do Gradle e a indexação

 ### 5. Execute o app
 - Selecione um emulador ou dispositivo físico
 - Clique em Run ▶️

 ---

 ## 🧩 Problemas enfrentados
 Durante o desenvolvimento destacaram-se os seguintes desafios:

 ### 1. Aprendizado do Jetpack Compose

 Controle de estado em Compose (quando usar State, StateFlow, remember, LaunchedEffect).

 Estilização: aprender a compor Modifier, MaterialTheme e criar temas consistentes.

 ### 2. Criação de rotas e integração com Retrofit

 Tratar erros de rede (timeout, HTTP error codes) e modelar respostas para evitar crashes.

 ### 3. Implementação de ViewModel e UseCase

 Separar responsabilidades entre UseCase (regras de negócio) e Repository (acesso a dados).

 Gerenciar injeção de dependências com Hilt e entender escopos (@Singleton, @ViewModelScoped). 

 ### 4. Navegação entre telas

 Uso de Navigation Compose e passagem de argumentos (strings, ids) entre destinos.

 Gerenciamento correto do back stack e evitar recriação de ViewModels desnecessárias.

 ---
 ## 🔗 Repositório do backend
 > https://github.com/DiogoKRosa/SrCamelo_API
