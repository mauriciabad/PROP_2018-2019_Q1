
function generateSchedule(response){
  var times = response.count;
  var div = document.getElementById('div-schedule');
  grid = document.createElement("div");
  grid.className = "schedule";
  div.innerHTML = '';
  div.appendChild(grid);
  for (const i in response.sessions) {
    console.log(response.sessions[i]);
    
    addSchedClass(grid, response.sessions[i].subject, response.sessions[i].classroom, response.sessions[i].weekDay, response.sessions[i].start, response.sessions[i].end, response.sessions[i].group)
  }
}

function addSchedClass(div, subject, room, day, start, end, group) {  
  let duration   = end - start;
  var clas       = document.createElement("div");
  clas.className = "class";
  clas.style     = "grid-row: " + start + " / span " + duration + "; grid-column: " + day + ";";
  
  nameSubject = document.createElement("div");
  nameSubject.className   = "subject";
  nameSubject.textContent = subject;

  nameGroup = document.createElement("div");
  nameGroup.className   = "group";
  nameGroup.textContent = group;

  nameRoom = document.createElement("div");
  nameRoom.className   = "room";
  nameRoom.textContent = room;

  clas.appendChild(nameSubject);
  clas.appendChild(nameGroup);
  clas.appendChild(nameRoom);

  div.appendChild(clas);
}