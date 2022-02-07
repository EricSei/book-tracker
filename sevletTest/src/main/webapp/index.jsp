<html>
<head>
<meta charset="UTF-8">
<title>Actor Request</title>
<!-- bootstrap style sheet link -->
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <!-- this jumbotron is copied from the bootstrap website -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Actor Request</h1>
                <p class="lead">Please enter the ID number for an actor below.</p>
            </div>
        </div>
        
        <!-- action should be the name of our servlet class -->
        <form action="ActorServlet" method="post">
        
                <label for="actor-id">Actor ID</label>
                <input type="number" id="actor-id" name="actor-id" min="1" max="200"><br><br>
                <!-- there are 200 actors in sakila database -->
            
            <input type="submit" value="Find" class="btn btn-primary">
        
        </form>
    </div>
</body>
</html>