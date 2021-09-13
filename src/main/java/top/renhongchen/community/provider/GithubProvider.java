package top.renhongchen.community.provider;


import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;
import top.renhongchen.community.dto.AccessTokenDTO;
import top.renhongchen.community.dto.GithubUserDTO;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        JSONObject jsonObject = JSONUtil.parseObj(accessTokenDTO);
        HttpResponse response = HttpRequest.post("https://github.com/login/oauth/access_token")
                .header(Header.ACCEPT, "application/json")
                .form(jsonObject)//表单内容
                .execute();
        //System.out.printf(response.body());
        JSONObject jsonObject1 = JSONUtil.parseObj(response.body());
        String accessToken = jsonObject1.get("access_token",String.class);
        return accessToken;
    }

    public GithubUserDTO getUser(String accessToken) {
        HttpResponse response = HttpRequest.get("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .execute();
        //System.out.printf(response.body());
        JSONObject jsonObject = JSONUtil.parseObj(response.body());
        GithubUserDTO githubUserDTO = jsonObject.toBean(GithubUserDTO.class);

        return githubUserDTO;
    }
}
