document.addEventListener("DOMContentLoaded", function () {
    if (sessionStorage.getItem("role") == "PASSENGER") {
        document.getElementById("flatOwnerSection").style.display = "none";
    }
    // document.getElementById("accountDetailsTable").addEventListener("DOMContentLoaded", function () {
    console.log("loaded");
    var ok = false;
    fetch(`http://localhost:9090/api/userData`, {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${sessionStorage.getItem("accessToken")}`,
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (!response.ok) {
                alert('Invalid Credentials');
            } else {
                ok = true;
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (ok) {
                document.getElementById("name").innerHTML = data.name;
                document.getElementById("email").innerHTML = data.email;
                document.getElementById("nationalId").innerHTML = data.nationalId;
                document.getElementById("nationality").innerHTML = data.nationality;
                document.getElementById("gender").innerHTML = data.gender;
                document.getElementById("age").innerHTML = data.age;
                document.getElementById("loyaltyPoints").innerHTML = data.loyaltyPoints;
            }
        })
        .catch((error) => {
            console.error(
                "There was a problem with the fetch operation:",
                error
            );
        });
    // })
    // document.getElementById("loginForm").addEventListener("submit", function (event) {
    //     event.preventDefault();

    //     var email = document.getElementById("inputemail").value;
    //     var hashedPassword = document.getElementById("inputpassword").value;

    //     let loginData = {
    //         email: email,
    //         hashedPassword: hashedPassword
    //     };

    //     console.log(loginData);

    //     createToken(loginData);
    // })
});

// function createToken(loginData){
//     fetch(`http://localhost:8088/api/login`, {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         body: JSON.stringify(loginData),
//     })
//     .then((response) => {
//         if (!response.ok) {
//             alert('Invalid Credentials');
//             document.getElementById("loginForm").reset();
//         } else {
//             ok = true;
//         }
//         return response.json();
//     })
//     .then((data) => {
//         console.log(data);
//         if(ok){
//             alert('Login Success');
//             sessionStorage.setItem("accessToken", data[0]);
//             sessionStorage.setItem("role", data[1]);

//             if(data[1] == "ADMIN"){
//                 window.location.href = "Admin_View/index.html";
//             }
//             else{
//                 window.location.href = "register.html";
//             }
//         }
//     })
//     .catch((error) => {
//         console.error(
//             "There was a problem with the fetch operation:",
//             error
//         );
//     });
// }