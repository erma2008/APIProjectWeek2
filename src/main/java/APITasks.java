import CompetitionPojo.CompetitionPojo;
import CountryPojo.ActiveCompetitions;
import CountryPojo.Country;
import ScorerPojo.TopScorer;
import TeamsPojo.TeamPojo;
import Utils.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static Utils.Constants.*;


/**
 * Created by nurkulov 12/26/19
 */
public class APITasks {
    private static HttpClient httpClient;
    private static URIBuilder uriBuilder;
    private static HttpGet httpGet;
    private static HttpResponse response;
    private static ObjectMapper objectMapper;




    /*
     * GET all soccer team names listed in given resource
     * Deserialization type: Pojo
     */

    public static List<String> getAllTeams() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        TeamPojo teamPojo = objectMapper.readValue(response.getEntity().getContent(), TeamPojo.class);
        List<String> teamNames = new ArrayList<>();
        for (int i = 0; i < teamPojo.getTeams().size(); i++) {
            teamNames.add(teamPojo.getTeams().get(i).getName());
        }
        return teamNames;
    }

    /*
     * GET names of all goalkeepers from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getAllGoalkeepers() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Map<String, Object> england = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        List<String> goalKeepers = new ArrayList<>();
        List<Map<String, Object>> squad = (List<Map<String, Object>>) england.get("squad");
        for (int i = 0; i < squad.size() - 1; i++) {
            if (squad.get(i).get("position").toString().equalsIgnoreCase("goalkeeper")) {
                goalKeepers.add(squad.get(i).get("name").toString());
            }
        }
        return goalKeepers;

    }

    /*
     * GET names of all defenders from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getDefenders() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Map<String, Object> england = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        List<String> defenders = new ArrayList<>();
        List<Map<String, Object>> squad = (List<Map<String, Object>>) england.get("squad");
        for (int i = 0; i < squad.size() - 1; i++) {
            if (squad.get(i).get("position").toString().equalsIgnoreCase("defender")) {
                defenders.add(squad.get(i).get("name").toString());
                System.out.println(squad.get(i).get("name"));
            }
        }
        return defenders;

    }

    /*
     * GET names of all midfielders from England team
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielders() throws IOException, URISyntaxException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Country england = objectMapper.readValue(response.getEntity().getContent(),
                Country.class);
        List<String> midfielder = new ArrayList<>();

        for (int i = 0; i < england.getSquad().size() - 1; i++) {
            if (england.getSquad().get(i).getPosition().toString().equalsIgnoreCase("midfielder")) {
                midfielder.add(england.getSquad().get(i).getName());
            }
        }
        System.out.println(midfielder);

        return midfielder;
    }

    /*
     * GET names of all midfielders from England team whose country is Brazil
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielderFromBrazil() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Country england = objectMapper.readValue(response.getEntity().getContent(),
                Country.class);
        List<String> midfielderFromBrazil = new ArrayList<>();

        for (int i = 0; i < england.getSquad().size() - 1; i++) {
            if (england.getSquad().get(i).getPosition().toString().equalsIgnoreCase("midfielder") &&
                    england.getSquad().get(i).getCountryOfBirth().toString().equalsIgnoreCase("Brazil")) {
                midfielderFromBrazil.add(england.getSquad().get(i).getName().toString());
                System.out.println(england.getSquad().get(i).getName().toString()
                );
            }
        }
        System.out.println(midfielderFromBrazil);

        return midfielderFromBrazil;
    }

    /*
     * GET names of all attackers from England team whose origin country is England
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getAttackerFromEngland() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/66");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Country england = objectMapper.readValue(response.getEntity().getContent(),
                Country.class);
        List<String> attackerFromEngland = new ArrayList<>();

        for (int i = 0; i < england.getSquad().size() - 1; i++) {
            if (england.getSquad().get(i).getPosition().equalsIgnoreCase("attacker") &&
                    england.getSquad().get(i).getCountryOfBirth().equalsIgnoreCase("England")) {
                attackerFromEngland.add(england.getSquad().get(i).getName());
            }
        }
        System.out.println(attackerFromEngland);

        return attackerFromEngland;
    }

    /*
     * GET name of Spain team coach
     * note: Spain team id is 77
     * Deserialization type: Pojo
     */
    public static List<String> getSpainCoach() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("/v2/teams/77");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        Country spain = objectMapper.readValue(response.getEntity().getContent(),
                Country.class);
        List<String> coachesFromSpain = new ArrayList<>();

        for (int i = 0; i < spain.getSquad().size(); i++) {
            if (spain.getSquad().get(i).getRole().equalsIgnoreCase("COACH")) {
                coachesFromSpain.add(spain.getSquad().get(i).getName());
            }
        }
        System.out.println(coachesFromSpain);

        return coachesFromSpain;
    }

    //expected 148 actual 149
    public static List<String> getAllCompetitions() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("v2/competitions/");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

        CompetitionPojo competitionPojo = objectMapper.readValue(response.getEntity().getContent(),
                CompetitionPojo.class);

        List<String> allCompetitions = new ArrayList<>();

        for (int i = 0; i < competitionPojo.getCompetitions().size(); i++) {

            allCompetitions.add(competitionPojo.getCompetitions().get(i).getName());

        }
        for (int i = 0; i < competitionPojo.getCompetitions().size(); i++) {

            System.out.print("\"" + competitionPojo.getCompetitions().get(i).getName() + "\"" + ",");

        }

        return allCompetitions;

    }

    /*
     * GET names of second highest scorrer from competitions of 2000 season
     * note: endpoint for competitions: `competitions/<year>/
     * note: endpoint for scorers: `competitions/<year>/scorers`
     * Deserialization type: Pojo and TypeReference
     */
    public static List<String> getSecondHighestScorer() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME)
                .setHost(BASEURL)
                .setPath("v2/competitions/2000/scorers");

        httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader(ACCEPT, JSON);
        httpGet.setHeader(CONTENT_TYPE, JSON);
        httpGet.setHeader(AUTH, TOKEN);

        response = httpClient.execute(httpGet);

        objectMapper = new ObjectMapper();

// Type Reference solution
//        Map<String, Object> comp2000 = objectMapper.readValue(response.getEntity().getContent(),
//                new TypeReference<Map<String, Object>>() {
//                });
//        List<String> name = new ArrayList<>();
//        List<Integer> scores = new ArrayList<>();
//
//        List<Map<String, Object>> scorers = (List<Map<String, Object>>) comp2000.get("scorers");
//
//        for (int i = 0; i < scorers.size(); i++) {
//            Map<String, Object> players = (Map<String, Object>) scorers.get(i).get("player");
//            name.add(players.get("name").toString());
//            scores.add((Integer) scorers.get(i).get("numberOfGoals"));
//        }
//
//
//        List<String> allCompetitions = new ArrayList<>();
//        int max = scores.get(0);
//        int min = scores.get(scores.size() - 1);
//        int secondMax = 0;
//        for(int i=0;i<scores.size();i++) {
//            if (scores.get(i) != max) {
//                secondMax = scores.get(i);
//                break;
//            }
//        }
//
//        for(int i=0;i<scores.size();i++){
//            if(scores.get(i)==secondMax){
//                allCompetitions.add(name.get(i));
//            }
//        }
//        System.out.println(allCompetitions);
//
//      return allCompetitions;

        TopScorer scores = objectMapper.readValue(response.getEntity().getContent(), TopScorer.class);

        List<String> footballer = new ArrayList<>();
        List<Integer> goals = new ArrayList<>();


        for (int i = 0; i < scores.getScorers().size(); i++) {
            footballer.add(scores.getScorers().get(i).getPlayer().getName());
            goals.add(scores.getScorers().get(i).getNumberOfGoals());
        }

        int max = goals.get(0);
        int secondMax = 0;


        for (int i = 0; i < goals.size(); i++) {
            if (goals.get(i) != max) {
                secondMax = goals.get(i);
                break;
            }
        }
        List<String> secondHighestScorers = new ArrayList<>();
        for (int i = 0; i < goals.size(); i++) {
            if (goals.get(i) == secondMax) {
                secondHighestScorers.add(footballer.get(i));
            }
        }
        System.out.println(secondHighestScorers);

        return secondHighestScorers;

    }
}
