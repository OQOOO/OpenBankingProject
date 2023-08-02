package kr.ac.kopo.controller.spring;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.util.Secrets;
import kr.ac.kopo.vo.BankAccountVO;

@RestController
public class ApiController {

	@RequestMapping("/getAccountListWithApi.sp")
	public List<BankAccountVO> apiTest(String email) {
		System.out.println("은행측 데이터 받기 성공 :"+email);
		
		SavingAccountDAO dao = new SavingAccountDAO();
		List<BankAccountVO> acList = dao.getAccountListWithEmail(email);
		
		return acList;
	}
	
	@GetMapping("/naverApi.sp")
	public ResponseEntity<String> getNaverApi() {
		Secrets sec = new Secrets();
		
		String query = "경제 연준 에너지 원자재 금융";
        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));
    
        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com/")
        .path("v1/search/news.json")
        .queryParam("query", query)
        .queryParam("display", 30)
        .queryParam("start", 1)
        .queryParam("sort", "sim")
        .encode()
        .build()
        .toUri();

        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();

        
        String naverId = sec.getNaverId();
        String naverSecrets = sec.getNaverSecrets();
        RequestEntity<Void> req = RequestEntity
        .get(uri)
        .header("X-Naver-Client-Id", naverId)
        .header("X-Naver-Client-Secret", naverSecrets)
        .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        System.out.println(result);
        
        return result;
	}
}