package hello.repository;

import hello.model.Friends;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FriendsRepositoyTest {
    @Autowired
    private MockMvc mvc;
    private FriendsRepository friendsRepository;


    @Before
    public void setUp(){
        friendsRepository = Mockito.mock(FriendsRepository.class);


        //Friends eric = new Friends("Eric","Jin");
        //friendsRepository.save(eric);

        //friendsRepository.insert(new Friends("Eric","Jin"));
        //friendsRepository.insert(new Friends("Faith","Jin"));
    }

    @Test
    public void findExistingFriend() throws Exception {
         //List<Friends> allFriends =  friendsRepository.findAll();

        List<Friends> tmpList = new ArrayList<>();
        tmpList.add(new Friends("Eric","Jin"));
        tmpList.add(new Friends("Faith","Jin"));
        given(friendsRepository.findAll()).willReturn(tmpList);


        MvcResult result = this.mvc.perform(get("/friends"))
                .andExpect(status().isOk())
                .andReturn();

        String raw = result.getResponse().getContentAsString();
        JSONObject json = new JSONObject(raw);


        JSONArray friends = json.getJSONObject("_embedded").getJSONArray("friends");
        int len = friends.length();
        JSONArray pending = new JSONArray();
        JSONArray accepted = new JSONArray();
        String email = null;

        System.out.println("Starts");
        for (int i = 0; i < len; i ++ ){
            JSONObject pairs = friends.getJSONObject(i);
            String fn = pairs.getString("firstName");
            String ln = pairs.getString("lastName");
            if (!pairs.get("email").equals(null))
                email = pairs.getString("email");
            if (!pairs.get("pendingFriendList").equals(null) ){
                pending = pairs.getJSONArray("pendingFriendList");
            }
            if (!pairs.get("acceptedFriendList").equals(null) ){
                accepted = pairs.getJSONArray("acceptedFriendList");
            }
            System.out.println(fn + ": " + ln + ": " + email + ": " + pending.toString() + accepted.toString());
        }
        System.out.println("Ends");
        /*
        Starts
Frodo: Baggins: sz@gmail.com: []["58c219d76c934d0651fea371"]
steve: zhang: sszang@gmail.com: []["123","456"]
Eric: Jin: sszang@gmail.com: []["123","456"]
Faith: Jin: jinyufan99@gmail.com: ["58c220ce160fe3981cfe93d1","58c219d76c934d0651fea371"]["123","456"]
Eric: Jin: jinyufan99@gmail.com: ["58c220ce160fe3981cfe93d1","58c219d76c934d0651fea371"]["123","456"]
Faith: Jin: jinyufan99@gmail.com: ["58c220ce160fe3981cfe93d1","58c219d76c934d0651fea371"]["123","456"]
Ends
         */
    }




}
