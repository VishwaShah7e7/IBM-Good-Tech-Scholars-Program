class Welcome extends HTMLElement {

    constructor() {

        super();

        console.log('INITIALIZING WELCOME VIEW');

        let template = document.getElementById('welcomeview');
        let templateContent = template.content;

        const shadow = this.attachShadow({mode: 'open'})
          .appendChild(templateContent.cloneNode(true));

    }

    connectedCallback() {

        let sr = this.shadowRoot;

        let usernameInput = sr.getElementById("usernameinput")
        let passwordInput = sr.getElementById("passwordinput")

        var phoneview = document.getElementById("phoneview");
        var mobileview = phoneview.getMobileView();

        if (loyalty.getCookie('access_token') != "" && loyalty.getCookie('id_token') != "") {
            let id_object = loyalty.parseJwt(loyalty.getCookie('id_token'))
            console.log(id_object)

            mobileview.innerHTML = "";

            let element = document.createElement('transactions-element')
            mobileview.appendChild(element);

            phoneview.showNavigation();
        } else {
            getAllUsers((users) => {
                users.forEach(user => {
                    var option = document.createElement("option");
                    option.text = user
                    selectUserInput.add(option)
                });
            })
        }

        signinButton.addEventListener("click", e => {
            this.signin(usernameInput.value, passwordInput.value)
        })
    }


    signin(username, password) {
        let sr = this.shadowRoot;

        var mobileview = sr.host.parentElement;
        mobileview.innerHTML = "";

        // create loading spinner first
        var element = document.createElement('loading-spinner-element');
        element.setAttribute("status", "Logging in...")
        mobileview.appendChild(element)

        loginWithAppId(username, password, (jsonWebToken) => {
            // when login complete,
            // re-initialize app?
            new Loyalty(this.mode);
            let id_object = loyalty.parseJwt(jsonWebToken.id_token)
            console.log(id_object)

            mobileview.innerHTML = "";

            let element = document.createElement('transactions-element')
            mobileview.appendChild(element);

            phoneview.showNavigation();
            // edge case when unable to sign in
        })
    }
}

try {
    customElements.define('welcome-element', Welcome);
} catch (err) {
    const h3 = document.createElement('h3')
    h3.innerHTML = err
    document.body.appendChild(h3)
}
