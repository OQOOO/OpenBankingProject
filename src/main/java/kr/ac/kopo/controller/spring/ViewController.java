package kr.ac.kopo.controller.spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.NewsItemVO;
import kr.ac.kopo.vo.NewsResponseVO;

@Controller
public class ViewController {
	@RequestMapping("/testIndex")
	public String index(Model model) {
		
		return "Index";
	}
	
	@RequestMapping("/toNews.sp")
	public String toNews(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<NewsResponseVO> NewsResponse = restTemplate.exchange("http://localhost:8080//OpenBanking/naverApi.sp", HttpMethod.GET, null, NewsResponseVO.class);
		NewsResponseVO rsVo = NewsResponse.getBody();
		List<NewsItemVO> newsList = rsVo.getItems();
        for(NewsItemVO vo : newsList) {
        	// 문자열 처리
        	vo.setTitle(vo.getTitle().replace("<b>", "").replace("<b\\/>", ""));
        	vo.setOriginallink(vo.getOriginallink().replace("<b>", "").replace("<b\\/>", ""));
        	vo.setLink(vo.getLink().replace("<b>", "").replace("<b\\/>", ""));
        	vo.setDescription(vo.getDescription().replace("<b>", "").replace("<b\\/>", ""));
        	vo.setPubDate(vo.getPubDate().replace("<b>", "").replace("<b\\/>", ""));
        	
        	try {
        		String originalDate = vo.getPubDate();
        		
        		SimpleDateFormat originalFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        		Date date = originalFormat.parse(originalDate);
        		
        		SimpleDateFormat koreanFormat = new SimpleDateFormat("yyyy년 M월 d일 a h시 m분");
        		koreanFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        		
        		String koreanDate = koreanFormat.format(date);
        		//System.out.println(koreanDate);
				vo.setPubDate(koreanDate);
        		
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        model.addAttribute("newsList", newsList);
        
		return "/news/NewsBoard";
	}
	
	@RequestMapping("/toGame.sp")
	public String toGame(Model model) {
		
		return "/game/GamePage";
	}
	
	@RequestMapping("/delCommentAd.sp")
	public String removeAdComment(Model model, String postId, int commentId) {
		
		BoardDAO dao = new BoardDAO();
		
		dao.delComment(commentId);
		
	
		
		return "/admin/pages/toBoard.do";
	}
}
