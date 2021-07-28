class Home extends HTMLElement {

    constructor() {
        super();

        console.log('INITIALIZING HOMESCREEN');

        let template = document.getElementById('homescreen');
        let templateContent = template.content;
        const shadow = this.attachShadow({
                mode: 'open'
            })
            .appendChild(templateContent.cloneNode(true));
    }

    showNotification(shadowRoot, notificationText) {
        var notifcationArea = shadowRoot.getElementById('notificationarea');
        notifcationArea.innerHTML = '';

        var message = document.createElement('div');
        message.innerHTML = notificationText
        message.className = 'notification';
        notifcationArea.appendChild(message);

        setTimeout(function(){
            message.remove()
        }, 2000);
    }

    connectedCallback() {

        var sr = this.shadowRoot;

        var tiles = sr.getElementById('APPTILES');

        var homescreen = this;

        tiles.addEventListener('APPTILE', e => {
            console.log('HOMESCREEN RECIEVED EVENT FROM TILE: ' + e.detail.eventData.name.toLocaleUpperCase());

            switch(e.detail.eventData.name){

                case 'thermi':
                    sr.host.parentElement.innerHTML = '<welcome-element mode="' + this.mode + '"></welcome-element>';
                    break;

                default:
                    let access_token = loyalty.getCookie('access_token')
                    if (access_token != "") {
                        homescreen.generateTransaction(access_token, sr, e)
                    } else {
                        homescreen.showNotification(sr, 'This app is unavailable.')
                    }
                    break;
            }
        });
    }
}

try {
    customElements.define('homescreen-element', Home);
} catch (err) {
    const h3 = document.createElement('h3')
    h3.innerHTML = err
    document.body.appendChild(h3)
}
