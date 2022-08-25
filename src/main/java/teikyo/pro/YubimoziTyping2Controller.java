package teikyo.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YubimoziTyping2Controller {

	// 質問数カウント
	int questionCnt = 0;

	// 正解数カウント
	String correctCnt = "0";

	// 出題問題数カウント
	String numCnt = "0";
	
	//BGM設定
	boolean musicOtn = true;

	// ランダムに選択された出題される問題のリスト
	List<Entry_table> question = new ArrayList<>();

	// ゲームの結果を保存するリスト
	List<Answer> answerList = new ArrayList<>();

	// スコア
	String score = "";

	@Autowired
	private YubimoziTyping2Repository repository;

	// タイトル画面
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		//List<Entry_table> list = entryLettersAll();

		//model.addAttribute("letterList", list);

		// model.addAttribute("entryletterList", elist);
		model.addAttribute("title", "YUBIMOJI type");
		return "index";
	}

	// 初級の問題出題
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public String entry(Model model) {

		model.addAttribute("title", "初級");

		questionCnt = 0;
		correctCnt = "0";
		question = new ArrayList<>();
		answerList = new ArrayList<>();

		question = questionAll();

		model.addAttribute("questionList", question);
		model.addAttribute("question", question.get(questionCnt));
		model.addAttribute("qcnt", questionCnt);
		return "entry";
	}

	// 上級の問題出題
	@RequestMapping(value = "/advanced", method = RequestMethod.GET)
	public String advanced(Model model) {

		model.addAttribute("title", "上級");

		questionCnt = 0;
		correctCnt = "0";
		question = new ArrayList<>();
		answerList = new ArrayList<>();

		question = questionAll();

		model.addAttribute("questionList", question);
		model.addAttribute("question", question.get(questionCnt));
		model.addAttribute("qcnt", questionCnt);
		return "advanced";
	}

	// 結果をentry.htmlから送ってきた後の処理
	@RequestMapping(value = "/answer", method = RequestMethod.POST)
		public String answer(Model model, @RequestParam("answerList") List<String> correctList,
				@RequestParam("inputList") List<String> inputList, @RequestParam("correctCount") String cCount,
				@RequestParam("scoreCount") String sre, @RequestParam("numCount") String nCount,
				@RequestParam("bgmOption") boolean option) {

			for (int i = 0; i < Integer.parseInt(nCount); i++) {
				Answer answer = new Answer(question.get(i).getAddress(), question.get(i).getName(), question.get(i).getYomi(),	inputList.get(i), correctList.get(i));
				answerList.add(answer);
			}

			correctCnt = cCount;
			score = sre;
			numCnt = nCount;
			musicOtn = option;

			return "redirect:/result";
		}

	// 結果表示
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(Model model) {
		model.addAttribute("title", "結果");
		String c = numCnt + " 問中 " + correctCnt + " 問正解！";
		String s = "スコア：" + score;
		model.addAttribute("correct", c);
		model.addAttribute("score", s);
		model.addAttribute("musicOtn", musicOtn);
		model.addAttribute("questions", question);

		model.addAttribute("answerList", answerList);

		return "result";

	}

	public List<Entry_table> entryLettersAll() {
		return repository.findAll();
	}

	public List<Letter_table> lettersAll() {
		return repository.findLettersAll();
	}

	public List<Entry_table> questionAll() {

		// DBの全問題のリスト
		List<Entry_table> questionList = entryLettersAll();

		// 残っている問題のリスト
		List<Entry_table> remainingQuestion = new ArrayList<>(questionList);

		// 乱数を生成
		Random random = new Random();

		// 132回繰り返す
		for (int k = 0; k < 132; k++) {

			int remainingCnt = remainingQuestion.size(); // 残っている問題の数
			int index = random.nextInt(remainingCnt); // ランダムに選択されたインデックス

			Entry_table q = remainingQuestion.get(index); // ランダムに選択された問題
			question.add(q); // ランダムに選択された問題をリストに追加

			int lastIndex = remainingCnt - 1; // 残っている問題リストの末尾のインデックス
			Entry_table lastQ = remainingQuestion.remove(lastIndex);// 残っている問題リストから末尾を削除

			// ランダムに選択された問題が末尾以外のときは、それを末尾の問題に置換
			if (index < lastIndex) {
				remainingQuestion.set(index, lastQ);
			}

		}
		return question;
	}

	// 遊び方画面
	@RequestMapping(value = "/rule", method = RequestMethod.GET)
	public String rule(Model model) {

		model.addAttribute("title", "遊び方");
		return "rule";
	}

	// 設定画面
	//@RequestMapping(value = "/configuration", method = RequestMethod.GET)
	//public String configuration(Model model) {

		//model.addAttribute("title", "設定");
		//return "configuration";
	//}

	// 指文字
	@RequestMapping(value = "/yubimoji_table", method = RequestMethod.GET)
	public String yubimoji_table(Model model) {
		model.addAttribute("title", "指文字表");
		//List<Letter_table> list = lettersAll();

		//model.addAttribute("lettersList", list);

		return "yubimoji_table";
	}
}
