window.onload = function() {
        document.forms.namedItem('getDragonByID').onsubmit = function() {
                document.forms.namedItem('getDragonByID').action = "http://localhost:8080/dragons/" + document.getElementById('id').value;
        };
};