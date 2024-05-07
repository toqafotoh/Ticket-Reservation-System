document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();

        var email = document.getElementById("inputemail").value;
        var hashedPassword = document.getElementById("inputpassword").value;

        let loginData = {
            email: email,
            hashedPassword: hashedPassword
        };

        console.log(loginData);

        createToken(loginData);
    })
});

function createToken(loginData){
    let ok = false;
    fetch(`http://localhost:9090/api/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
    })
    .then((response) => {
        if (!response.ok) {
            alert('Invalid Credentials');
            document.getElementById("loginForm").reset();
        } else {
            ok = true;
        }
        return response.json();
    })
    .then((data) => {
        console.log(data);
        if(ok){
            alert('Login Success');
            sessionStorage.setItem("accessToken", data[0]);
            sessionStorage.setItem("role", data[1]);

            if(data[1] == "ADMIN"){
                window.location.href = "Admin_View/index.html";
            }
            else{
                window.location.href = "profile_user.html";
            }
        }
    })
    .catch((error) => {
        console.error(
            "There was a problem with the fetch operation:",
            error
        );
    });
}