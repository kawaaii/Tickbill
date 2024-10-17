# Tickbill

Tickbill is an open-source alternative to existing POS systems. It utilizes Maven for dependency management and is built
with Java Swing.

---

### Required Dependencies

- MySQL (for database management)
- Java JDK 22
- Your preferred IDE (e.g., NetBeans, IntelliJ IDEA)

---

### How to use?

1. **Clone this repository:**<br/>
   `git clone https://github.com/kawaaii/Tickbill`
2. **Open the project** in your preferred IDE.
3. **Compile and run**.

---
> [!WARNING]
> Currently Tickbill doesn't account for any database mismatches, so make sure you execute SQL
> script [pos.sql](src/main/java/com/hridaya/tickbill/database/pos.sql) before running the project.

> [!NOTE]
> Don't forget to change your port number
> in [DBConnection.java](src/main/java/com/hridaya/tickbill/database/DbConnection.java).

This project is licensed under [MIT](LICENSE).