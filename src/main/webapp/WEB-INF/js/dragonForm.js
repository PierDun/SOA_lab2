function addDragon() {
    const addDragonForm = document.forms.namedItem("addDragonForm");
    let formData = new FormData(addDragonForm);
    let request = new XMLHttpRequest();
    const newDragon = '<dragon>' +
        '         <id>0</id>' +
        '         <name>' + formData.get('name') + '</name>' +
        '         <coordinates>' +
        '            <x>' + formData.get('x') + '</x>' +
        '            <y>' + formData.get('y') + '</y>' +
        '         </coordinates>' +
        '         <age>' + formData.get('age') + '</age>' +
        '         <type>' + formData.get('type') + '</type>' +
        '         <color>' + formData.get('color') + '</color>' +
        '         <description>' + formData.get('description') + '</description>' +
        '         <cave>' +
        '            <depth>' + formData.get('depth') + '</depth>' +
        '         </cave>' +
        '      </dragon>'
    request.open("POST", "/dragons");
    request.send(newDragon);
    request.onreadystatechange = function() {
        getErrorMsg(request);
    };
}

function updateDragon() {
    const updateDragonForm = document.forms.namedItem("updateDragonForm");
    let formData = new FormData(updateDragonForm);
    const newDragon = '<dragon>' +
        '         <id>' + formData.get('id') + '</id>' +
        '         <name>' + formData.get('name') + '</name>' +
        '         <coordinates>' +
        '            <x>' + formData.get('x') + '</x>' +
        '            <y>' + formData.get('y') + '</y>' +
        '         </coordinates>' +
        '         <age>' + formData.get('age') + '</age>' +
        '         <type>' + formData.get('type') + '</type>' +
        '         <color>' + formData.get('color') + '</color>' +
        '         <description>' + formData.get('description') + '</description>' +
        '         <cave>' +
        '            <depth>' + formData.get('depth') + '</depth>' +
        '         </cave>' +
        '      </dragon>'
    let request = new XMLHttpRequest();

    request.responseType = 'text';
    request.open("PUT", "/dragons/" + formData.get('id'));
    request.send(newDragon);
    request.onreadystatechange = function() {
        getErrorMsg(request);
    };
}

function getErrorMsg(request) {
    if (request.status === 200) {
        window.location = '/dragons';
    }
}