# ToDo-Api

- 이 프로젝트는 Spring Boot 3.x과 MySQL을 활용한 할 일(ToDo) 관리 API입니다.<br>
사용자는 CRUD 기능을 활용하여 할 일을 생성, 조회, 삭제할 수 있습니다.

✅ 핵심 기능:

- ToDo 목록 조회 (GET /api/todos)<br>
- ToDo 단건 조회 (GET /api/todos/{id})<br>
- ToDo 생성 (POST /api/todos)<br>
- ToDo 삭제 (DELETE /api/todos/{id})<br>
- 완료 상태 토글 (PUT /api/todos/{id}/toggle-completed) (추가 API)

✅ 기술 스택:

- Spring Boot 3.4.2<br>
- Spring Data JPA<br>
- MySQL<br>
- Swagger API Docs<br>
- JUnit5 & Mockito (테스트 케이스 작성)<br>
- Lombok (코드 간결화)<br>
2. 소스 빌드 및 실행 방법
- 프로젝트 실행 방법
1️⃣ 필수 환경 설치
JDK 17 이상<br> 
MySQL 8.0+<br>
Maven 3.x<br>

2️⃣ 프로젝트 다운로드 및 실행

# GitHub에서 소스 코드 클론
git clone https://github.com/your-github-username/todo-api.git<br>
cd todo-api

# Maven 패키징 및 실행
mvn clean install<br>
mvn spring-boot:run
3️⃣ MySQL 설정
MySQL 데이터베이스 생성

CREATE DATABASE todo_db;<br>
CREATE USER 'todo_user'@'localhost' IDENTIFIED BY '1234';<br>
GRANT ALL PRIVILEGES ON todo_db.* TO 'todo_user'@'localhost';<br>
FLUSH PRIVILEGES;<br>
application.properties (또는 application.yml) 설정<br>

spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?useSSL=false&serverTimezone=Asia/Seoul<br>
spring.datasource.username=todo_user<br>
spring.datasource.password=1234<br>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver<br>

# JPA 설정
spring.jpa.hibernate.ddl-auto=update<br>
spring.jpa.show-sql=true<br>
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect<br>
Swagger 실행 후 API 테스트
http://localhost:8080/swagger-ui/index.html 접속<br>
API를 직접 실행하여 데이터 생성 및 조회 테스트<br>
3. 주력 사용 라이브러리 및 사용 이유
라이브러리	설명
- Spring Boot	빠른 개발을 위한 경량화 프레임워크<br>
- Spring Data JPA	ORM을 사용하여 MySQL과 연동<br>
- Spring Web	RESTful API 개발을 위한 필수 라이브러리<br>
- Lombok	@Getter, @Setter, @Builder 등 코드 간소화를 위한 라이브러리<br>

# 4. API 명세 (Swagger 적용)
Swagger 주소:

http://localhost:8080/swagger-ui/index.html<br>
📌 주요 API<br>
✅ ToDo 목록 조회<br>
GET /api/todos<br>
🔹 Response Example<br>
[
    { "id": 1, "title": "스터디 준비", "completed": false, "createdAt": "2024-02-17T10:00:00" },
    { "id": 2, "title": "코딩 연습", "completed": true, "createdAt": "2024-02-17T12:30:00" }
]<br>
✅ ToDo 생성<br>

POST /api/todos<br>
Content-Type: application/json<br>
🔹 Request Example<br>
{
    "title": "새로운 할 일",
    "completed": false
}<br>
🔹 Response Example<br>
{
    "id": 3,
    "title": "새로운 할 일",
    "completed": false,
    "createdAt": "2024-02-17T14:00:00"
}<br>
✅ 완료 상태 변경<br>
PUT /api/todos/{id}/toggle-completed<br>
🔹 Response Example<br>
{
    "id": 3,
    "title": "새로운 할 일",
    "completed": true,
    "createdAt": "2024-02-17T14:00:00"
}<br>
✅ ToDo 삭제<br>
DELETE /api/todos/{id}<br>
🔹 Response: 204 No Content<br>

# 5. 테스트 케이스 작성
📌 JUnit & Mockito 테스트 코드<br>
📍 TodoServiceTest.java<br>

@SpringBootTest
public class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void testCreateTodo() {
        Todo todo = new Todo(null, "테스트 할 일", false, LocalDateTime.now());
        Mockito.when(todoRepository.save(Mockito.any())).thenReturn(todo);

        Todo savedTodo = todoService.createTodo(todo);
        assertEquals("테스트 할 일", savedTodo.getTitle());
    }
}<br>
📍 TodoControllerTest.java<br>

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void testGetTodos() throws Exception {
        List<Todo> todos = List.of(new Todo(1L, "할 일 1", false, LocalDateTime.now()));
        Mockito.when(todoService.getAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("할 일 1"));
    }
}<br>
🎯 추가 기능<br>
✅ 완료 상태 토글 API 추가 (PUT /api/todos/{id}/toggle-completed)<br>
✅ Swagger 적용으로 API 문서 자동화<br>
✅ 테스트 코드 작성으로 신뢰성 확보
