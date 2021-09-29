package im.minyoung.todolist.Repository;
//repository = 통로라고 보면됨 interface로 작성

import org.springframework.stereotype.Repository;

import im.minyoung.todolist.Model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
