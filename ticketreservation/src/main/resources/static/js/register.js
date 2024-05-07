document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("signupForm").addEventListener("submit", function (event) {
        event.preventDefault();

        var name = document.getElementById("inputname").value;
        var email = document.getElementById("inputemail").value;
        var hashedPassword = document.getElementById("inputpassword").value;
        var nationalId = document.getElementById("inputNID").value;
        var nationality = document.getElementById("inputNationality").value;
        var phoneNum = document.getElementById("inputNumber").value;
        var dob = new Date(document.getElementById("dateInput").value);
        var role = document.getElementById("inputrole").value;
        var gender = document.getElementById("inputgender").value;

        var age = calculateAge(dob);

        let userData = {
            email: email,
            name: name,
            hashedPassword: hashedPassword,
            nationalId: nationalId,
            nationality: nationality,
            phoneNum: phoneNum,
            gender: gender,
            role: role,
            age: age
        };

        console.log(userData);

        createUser(userData);
    })
});

function createUser(userData){
    fetch(`http://localhost:9090/api/signup`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
    })
        .then((response) => {
            if (!response.ok) {
                alert('Invalid Credentials');
                document.getElementById("signupForm").reset();
            } else {
                alert('User Created Successfully');
                window.location.href = "login.html";
            }
            return response ;
        })
        .catch((error) => {
            console.error(
                "There was a problem with the fetch operation:",
                error
            );
        });
}

function calculateAge(birthday) { 
    var ageDifMs = Date.now() - birthday.getTime();
    var ageDate = new Date(ageDifMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}