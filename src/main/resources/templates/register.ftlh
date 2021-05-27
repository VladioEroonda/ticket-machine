<#import "parts/page-template.ftlh" as p>

<@p.template>

    <form action="/register-new-user" method="post">
        <p>Name: </p><input type="text" placeholder="" name="name" required><br>
        <p>Surname: </p><input type="text" placeholder="" name="surname" required><br>
        <p>Email: </p><input type="text" placeholder="" name="email" required><br>
        <p>Password: </p><input type="text" placeholder="" name="password" required><br>
        <p>Repeat your password: </p><input type="text" placeholder="" name="password2" required><br>
        <p>Phone number: </p><input type="text" placeholder="" name="phoneNumber" required><br>
        <button type="submit">Register</button>
    </form>
    <p align="center">All fields are required</p>
    <br><br>
    <p align="center">Already have an account? <a href="/login">Log in</a>.</p>



</@p.template>