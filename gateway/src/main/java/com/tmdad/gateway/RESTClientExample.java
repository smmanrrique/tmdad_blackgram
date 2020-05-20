package com.tmdad.gateway;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RESTClientExample {

    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8081/user";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);

        System.out.println("----"+response.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("userName");
        assertThat(name.asText(), notNullValue());

//        WebClient client3 = WebClient
//                .builder()
//                .baseUrl("http://localhost:8080")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .build();
//        WebClient client = WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//                .build();
//
//        WebClient.RequestBodySpec uri2 = client3
//                .post()
//                .uri(URI.create("/resource"));
//
//        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
//                .create("http://localhost:8080")
//                .post()
//                .uri(URI.create("/resource"))
//                .body(BodyInserters.fromObject("data"));
//
//        BodyInserter<Object, ReactiveHttpOutputMessage> inserter3
//                = BodyInserters.fromObject(new Object());
//
//        WebClient.ResponseSpec response1 = uri1
//                .body(inserter3)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
//                .acceptCharset(Charset.forName("UTF-8"))
//                .ifNoneMatch("*")
//                .ifModifiedSince(ZonedDateTime.now())
//                .retrieve();
    }

    public static class User {
        String userName;
        String password;

        public User(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }
}
//    @PropertySource("classpath:application.properties")
//    public class Main
//    {
//        /**
//         * Setting up logger
//         */
//        private static final Logger LOGGER = getLogger(Main.class);
//        public static void main(String[] args) throws IOException
//        {
//            LOGGER.debug("Starting REST Client!!!!");
//            /**
//             *
//             * This is going to setup the REST server configuration in the applicationContext
//             * you can see that I am using the new Spring's Java Configuration style and not some OLD XML file
//             *
//             */
//            ApplicationContext context = new AnnotationConfigApplicationContext(RESTConfiguration.class);
//            /**
//             *
//             * We now get a RESTServer bean from the ApplicationContext which has all the data we need to
//             * log into the REST service with.
//             *
//             */
//            RESTServer mRESTServer = context.getBean(RESTServer.class);
//            /**
//             *
//             * Setting up data to be sent to REST service
//             *
//             */
//            Map<String, String> vars = new HashMap<String, String>();
//            vars.put("id", "JS01");
//            /**
//             *
//             * Doing the REST call and then displaying the data/user object
//             *
//             */
//            try
//            {
//            /*
//                This is code to post and return a user object
//             */
//                RestTemplate rt = new RestTemplate();
//                rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
//                rt.getMessageConverters().add(new StringHttpMessageConverter());
//                String uri = new String("http://" + mRESTServer.getHost() + ":8080/springmvc-resttemplate-test/api/{id}");
//                User u = new User();
//                u.setName("Johnathan M Smith");
//                u.setUser("JS01");
//                User returns = rt.postForObject(uri, u, User.class, vars);
//                LOGGER.debug("User:  " + u.toString());
//            }
//            catch (HttpClientErrorException e)
//            {
//                /**
//                 *
//                 * If we get a HTTP Exception display the error message
//                 */
//                LOGGER.error("error:  " + e.getResponseBodyAsString());
//                ObjectMapper mapper = new ObjectMapper();
//                ErrorHolder eh = mapper.readValue(e.getResponseBodyAsString(), ErrorHolder.class);
//                LOGGER.error("error:  " + eh.getErrorMessage());
//            }
//            catch(Exception e)
//            {
//                LOGGER.error("error:  " + e.getMessage());
//            }
//        }
//    }

