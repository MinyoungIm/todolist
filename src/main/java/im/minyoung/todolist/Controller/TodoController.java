package im.minyoung.todolist.Controller;
// 실제로 화면쪽을 처리해주는 클래스

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;

import im.minyoung.todolist.Model.Todo;
import im.minyoung.todolist.Service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Todo> allTodo = todoService.searchAll();
		model.addAttribute("allTodo", allTodo);
		model.addAttribute("todo", new Todo()); // todo객체를 생성해줘야함
		return "app-to-do";
	}
	
	@PostMapping("/")
	public String createTodo(@Valid Todo todo, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<Todo> allTodo = todoService.searchAll();
			model.addAttribute("allTodo", allTodo);
			model.addAttribute("todo", todo); // 입력되있는걸 todo로 받음
			return "app-to-do";
		}
		
		// 오류가 없으면 Todo를 신규등록
		todoService.addTodo(todo);
		return "redirect:/"; // root로 리다이렉트
	}

	@PostMapping("/done")
	public String doneTodo(@RequestParam(name="id") Integer todoId) {
		Todo updateTodo = todoService.findById(todoId);
		updateTodo.setDone(true);
		todoService.addTodo(updateTodo);
		//값이 바뀌어야 하니까 리다이렉트
		return "redirect:/";
	}
	
//	삭제버튼 클릭시 처리
	@PostMapping("/deleteAll")
	public String deleteAll() {
		todoService.deleteAllTodo();  
		return "redirect:/";
	}
	
	
	
}
