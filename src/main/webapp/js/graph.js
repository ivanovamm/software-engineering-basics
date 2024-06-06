//получение контекста
const canvas = document.getElementsByTagName("canvas")[0];
const context = canvas.getContext("2d");

// координатная ось

//функция для рисования стрелки в конце линии
function drawArrowhead(context, fromX, fromY, toX, toY, color) {
    let headLength = 10;
    let angle = Math.atan2(toY - fromY, toX - fromX);
    context.beginPath();
    context.strokeStyle = color;
    context.moveTo(toX, toY);
    context.lineTo(toX - headLength * Math.cos(angle - Math.PI / 6),
        toY - headLength * Math.sin(angle - Math.PI / 6));
    context.lineTo(toX - headLength * Math.cos(angle + Math.PI / 6),
        toY - headLength * Math.sin(angle + Math.PI / 6));
    context.closePath();
    context.fillStyle="white";
    context.fill();
    context.stroke();
}

//функция для проведения линии
function drawLine(context, fromX, fromY, toX, toY, width) {
    context.beginPath();
    context.moveTo(fromX, fromY);
    context.lineTo(toX, toY);
    context.strokeStyle = "white";
    context.lineWidth = width;
    context.stroke();
}

//функция для рисования точки попадания
function drawDot(context, x, y, color) {
    let radius = 2;
    context.beginPath();
    context.arc(x, y, radius, 0, 2 * Math.PI);
    context.lineWidth = 2;
    context.strokeStyle = color;
    context.stroke();
    context.fillStyle = color;
    context.fill();
}

//переводит координаты в пиксели
function numToPixels(x, y) {
    let mouseX, mouseY;
    mouseX = 200 + x * 30;
    mouseY = 200 - y * 30;
    return [mouseX, mouseY];
}

function getSelectedRValue() {
    const checkboxes = document.querySelectorAll('input[name="optionR"]:checked');

    if (checkboxes.length > 0) {
        return checkboxes[0].value;
    }

    return null;
}

canvas.addEventListener("click", function (event) {
    const rVal = getSelectedRValue();
    if (rVal === null) {

       alert("Пожалуйста, выберете координату r!!!!!!!!!!!!!!!!!!!")

    }
    let mouseX = event.clientX - canvas.getBoundingClientRect().left;
    let mouseY = event.clientY - canvas.getBoundingClientRect().top;

    //если клик попал в область графика переходим к обработке
    if (mouseX <= 350 && mouseX >= 50 && mouseY <= 350 && mouseY >= 50) {
        //рисуем точку
        drawDot(context, mouseX, mouseY, "white");
        let x;
        let y;
        //вычисляем x и y
        if (mouseX > 200) {
            mouseX -= 200
            x = Math.floor(mouseX / 30);
            x = (x + ((mouseX - x * 30) * (1 / 30))).toFixed(2);
        } else {
            mouseX -= 50
            x = Math.floor(mouseX / 30);
            x = ((x + ((mouseX - x * 30) * (1 / 30))) - 5).toFixed(2);
        }
        if (mouseY > 200) {
            mouseY -= 200
            y = Math.floor(mouseY / 30);
            y = -((y + ((mouseY - y * 30) * (1 / 30))).toFixed(2));
        } else {
            mouseY -= 50
            y = Math.floor(mouseY / 30);
            y = (5 - (y + ((mouseY - y * 30) * (1 / 30)))).toFixed(2);
        }
        console.log(x);
        console.log(y);
        console.log(rVal);
        $.get(`http://localhost:8080/lab2_4-1.0-SNAPSHOT/checkArea?action=submitForm&x=${x.toString()}&y=${y.toString()}&r=${rVal}`)
            .done(function(response) {
                console.log(response);
                window.location.reload();
            })
            .fail(function(error) {
                console.error('Error:', error);
            });
    }
});



function drawCanvas(context){
    //ось x
    drawLine(context, 20, 200, 380, 200, 2);
    drawArrowhead(context, 20, 200, 380, 200);

    //ось y
    drawLine(context, 200, 20, 200, 380, 2)
    drawArrowhead(context, 200, 380, 200, 20);

    //подписи к осям
    context.font = "13px Serif";
    context.fillStyle = "white";
    context.fillText("0", (canvas.width / 2) - 11, canvas.height / 2 + 10);
    context.fillText("X", canvas.width - 20, canvas.height / 2 + 15);
    context.fillText("Y", canvas.width / 2 - 15, 20);

    //разметка оси y
    drawLine(context, 195, 230, 205, 230, 2)
    context.fillText("-1", (canvas.width / 2) - 20, canvas.height / 2 + 31);

    drawLine(context, 195, 260, 205, 260, 2)
    context.fillText("-2", (canvas.width / 2) - 20, canvas.height / 2 + 60);

    drawLine(context, 195, 290, 205, 290, 2)
    context.fillText("-3", (canvas.width / 2) - 20, canvas.height / 2 + 91);

    drawLine(context, 195, 320, 205, 320, 2)
    context.fillText("-4", (canvas.width / 2) - 20, canvas.height / 2 + 121);

    drawLine(context, 195, 350, 205, 350, 2)
    context.fillText("-5", (canvas.width / 2) - 20, canvas.height / 2 + 151);


    drawLine(context, 195, 170, 205, 170, 2)
    context.fillText("1", (canvas.width / 2) - 15, canvas.height / 2 - 31);

    drawLine(context, 195, 140, 205, 140, 2)
    context.fillText("2", (canvas.width / 2) - 15, canvas.height / 2 - 60);

    drawLine(context, 195, 110, 205, 110, 2)
    context.fillText("3", (canvas.width / 2) - 15, canvas.height / 2 - 91);

    drawLine(context, 195, 80, 205, 80, 2)
    context.fillText("4", (canvas.width / 2) - 15, canvas.height / 2 - 121);

    drawLine(context, 195, 50, 205, 50, 2)
    context.fillText("5", (canvas.width / 2) - 15, canvas.height / 2 - 151);

    // разметка оси x
    drawLine(context, 230, 195, 230, 205, 2)
    context.fillText("1", (canvas.width / 2) + 27, canvas.height / 2 + 15);

    drawLine(context, 260, 195, 260, 205, 2)
    context.fillText("2", (canvas.width / 2) + 57, canvas.height / 2 + 15);

    drawLine(context, 290, 195, 290, 205, 2)
    context.fillText("3", (canvas.width / 2) + 87, canvas.height / 2 + 15);

    drawLine(context, 320, 195, 320, 205, 2)
    context.fillText("4", (canvas.width / 2) + 117, canvas.height / 2 + 15);

    drawLine(context, 350, 195, 350, 205, 2)
    context.fillText("5", (canvas.width / 2) + 147, canvas.height / 2 + 15);

    drawLine(context, 170, 195, 170, 205, 2)
    context.fillText("-1", (canvas.width / 2) - 38, canvas.height / 2 + 15);

    drawLine(context, 140, 195, 140, 205, 2)
    context.fillText("-2", (canvas.width / 2) - 68, canvas.height / 2 + 15);

    drawLine(context, 110, 195, 110, 205, 2)
    context.fillText("-3", (canvas.width / 2) - 98, canvas.height / 2 + 15);

    drawLine(context, 80, 195, 80, 205, 2)
    context.fillText("-4", (canvas.width / 2) - 128, canvas.height / 2 + 15);

    drawLine(context, 50, 195, 50, 205, 2)
    context.fillText("-5", (canvas.width / 2) - 158, canvas.height / 2 + 15);

    const rVal = getSelectedRValue();
    //если r не выбран подсвечиваем его и завершаем обработчик
    if (rVal !== undefined) {
        //настройка context и отрисовка круга
        context.beginPath();
        context.strokeStyle='white';
        context.globalAlpha = 0.5
        context.arc(200, 200, rVal / 2 * 30, Math.PI, - Math.PI / 2, false);
        context.lineTo(200, 200);
        context.lineWidth=1
        context.closePath();
        context.fillStyle = '#4390BDFF';
        context.fill();
        context.stroke();
        //отрисовка квадрата
        context.beginPath();
        context.globalAlpha = 0.5
        context.moveTo(200, 200)
        context.lineTo(200 + (rVal * 30), 200);
        context.lineTo(200 + (rVal * 30), 200 - (rVal/2 * 30))
        context.lineTo(200, 200 - (rVal/2 * 30))
        context.lineTo(200, 200)
        context.closePath();
        context.fill();
        context.stroke();
        //отрисовка треугольника
        context.beginPath();
        context.moveTo(200, 200)
        context.lineTo(200 - (rVal * 30), 200);
        context.lineTo(200, 200 + (rVal/2 * 30))
        context.lineTo(200, 200)
        context.closePath();
        context.fill();
        context.stroke();
        //сброс контекста
        context.globalAlpha = 1
        context.lineWidth=2
        context.strokeStyle='white';
    }

    //запрос серверу и отрисовка точек
    $.get("http://localhost:8080/lab2_4-1.0-SNAPSHOT/getPoints")
        .done(function(response) {
            console.log(response);
            let arr = response.split(' ');
            let i = 0;
            let temp = [];
            for (let num of arr){
                if (i%3===2){
                    let hit = num === "true" ? "green": "red";
                    drawDot(context, numToPixels(temp[0], temp[1])[0], numToPixels(temp[0], temp[1])[1], hit);
                    temp = [];
                }else{
                    temp.push(num);
                }
                i++;
            }
        });

}

const checkboxes = document.querySelectorAll('input[name="optionR"]');

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function() {
        console.log(`Checkbox with value ${this.value} is checked: ${this.checked}`);
        context.clearRect(0, 0, canvas.width, canvas.height);
        drawCanvas(context);
    });
});

drawCanvas(context);
