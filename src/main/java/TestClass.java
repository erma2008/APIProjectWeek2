import CountryPojo.Country;
import TeamsPojo.TeamPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestClass {
    private static HttpClient httpClient;
    private static URIBuilder uriBuilder;
    private static HttpGet httpGet;
    private static HttpResponse response;
    private static ObjectMapper objectMapper;
    private static final String JSON = "apllication/json";
    private static final String TOKEN = "0781898207d84546abfea4f2c29ef6e5";
    private static final String BASEURL = "api.football-data.org/";
    private static final String SCHEME = "https";


    @Test
    public void test1() throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("api.football-data.org/")
                .setPath("/v2/teams");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("X-Auth-Token", "0781898207d84546abfea4f2c29ef6e5");

        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        TeamPojo teamPojo = objectMapper.readValue(response.getEntity().getContent(), TeamPojo.class);
        List<String> teamNames = new ArrayList<>();
        for (int i = 0; i < teamPojo.getTeams().size(); i++) {
            teamNames.add(teamPojo.getTeams().get(i).getName());
        }

        System.out.println(teamNames);
    }

    @Test
    public void test2() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", JSON);
        httpGet.setHeader("Content-Type", JSON);
        httpGet.setHeader("X-Auth-Token", TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Map<String, Object> england = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        // List<String> goalKeepers=new ArrayList<>();
        List<Map<String, Object>> squad = (List<Map<String, Object>>) england.get("squad");
        System.out.println(squad.size());
        for (int i = 0; i < squad.size(); i++) {
            //Map<String, Object> dataItem = (Map<String, Object>) england.get(i);
            //if (dataItem.get("position").toString().equalsIgnoreCase("Goalkeeper")) {
            if (squad.get(i).get("position").toString().equalsIgnoreCase("goalkeeper")) {
                System.out.println(squad.get(i).get("name").toString());

            }


        }
    }


    @Test
    public void test3() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", JSON);
        httpGet.setHeader("Content-Type", JSON);
        httpGet.setHeader("X-Auth-Token", TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Country country = objectMapper.readValue(response.getEntity().getContent(),
                Country.class);
        List<String> midfielder = new ArrayList<>();

        for (int i = 0; i < country.getSquad().size() - 1; i++) {
            if (country.getSquad().get(i).getPosition().toString().equalsIgnoreCase("midfielder")) {
                midfielder.add(country.getSquad().get(i).getName().toString());
                System.out.println(country.getSquad().get(i).getName().toString()
                );
            }
        }
    }

    @Test
    public void test4() throws IOException, URISyntaxException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", JSON);
        httpGet.setHeader("Content-Type", JSON);
        httpGet.setHeader("X-Auth-Token", TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Map<String, Object> england = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        List<String> attackersFromEngland = new ArrayList<>();
        List<Map<String, Object>> squad = (List<Map<String, Object>>) england.get("squad");
        for (int i = 0; i < squad.size() - 1; i++) {
            if (squad.get(i).get("position").toString().equalsIgnoreCase("Attacker") &&
                    squad.get(i).get("nationality").toString().equalsIgnoreCase("England")) {
                attackersFromEngland.add(squad.get(i).get("name").toString());
                System.out.println(squad.get(i).get("name"));
            }

        }
    }

/*
Muammers

"WC Qualification","Superliga Argentina", "Supercopa Argentina", "Primera B Nacional", "WC Qualification", "A League", "FFA Cup",
                "Playoffs 1/2" ,
            "Bundesliga", "ÖFB Cup",
                "Erste Liga",
                "Jupiler Pro League", "Playoffs II", "Coupe de Belgique", "Supercoupe de Belgique", "Division 1B", "LFPB", "Premier Liga", "Série D", "Série A",
            "Série C", "Série B", "Copa do Brasil", "Kupa na Bulgarija", "A PFG", "Canadian Championship", "Playoffs 1/2", "Supercopa de Chile", "Primera División", "Chinese Super League",
            "Superliga de Colombia", "Liga Postobón", "Prva Liga", "Synot Liga", "Superliga", "Play Offs 1/2", "DBU Pokalen", "Copa Pilsener Serie A", "Championship", "League One", "League Two",
            "Premier League", "FA Cup", "Football League Cup", "FA Community Shield", "National League", "Meistriliiga", "WC Qualification", "UEFA Women's EURO", "UEFA Europa League",
            "UEFA Champions League", "European Championship", "Veikkausliiga", "Suomen Cup", "Ligue 2", "Coupe de France", "Playoffs 1/2", "Coupe de la Ligue", "Playoffs 2/3", "Ligue 1",
            "Trophée des Champions", "DFB-Pokal", "Regionalliga", "Frauen Bundesliga", "2. Bundesliga", "DFL Super Cup", "Bundesliga", "3. Liga", "Greek Cup", "Super League", "Magyar Kupa", "NB I",
            "Úrvalsdeild", "I-League", "Ligat ha'Al", "Serie B", "Coppa Italia", "Serie C", "Supercoppa", "Serie A", "J. League Division 2", "J.League Cup", "J. League", "Super Cup", "Virslīga",
            "A Lyga", "Premier League", "Copa MX", "SuperCopa MX", "Liga MX", "Eredivisie", "Jupiler League", "KNVB Beker", "Johan Cruijff Schaal", "Premiership", "League Cup", "Playoffs 1/2",
            "1. divisjon", "Tippeligaen", "WC Qualification", "Liga Panameña de Fútbol", "Primera División", "Superpuchar Polski", "Puchar Polski", "Liga2 Cabovisão", "Primeira Liga",
            "Supertaça Cândido de Oliveira", "Taça de Portugal", "Premier Division", "Liga II", "Supercupa României", "Liga I", "Russian Super Cup", "RFPL", "FNL", "Russian Cup", "Playoffs 1/2",
            "Scottish Cup", "Premier League", "Championship", "Playoffs 2/3", "ABSA Premiership", "Copa America", "Copa Sudamericana", "WC Qualification", "Copa del Rey", "Primera Division",
            "Segunda División", "Supercopa de España", "Superettan", "Playoffs 2/3", "Playoffs 1/2", "Allsvenskan", "Schweizer Pokal", "Super League", "Süper Lig", "1. Lig", "TFF Süper Kupa",
            "Playoffs 1/2", "Superkubok Ukrainy", "Premier Liha", "Kubok Ukrainy", "MLS", "Primera División", "Primera División", "V-League", "Welsh Premier League", "FIFA World Cup"

 */


/*
*/


}


