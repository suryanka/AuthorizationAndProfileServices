In this Project two microservices are developed.
Authorization Service. --> This micro service is responsible for Authorizing the User by Username and Password.
Profile Service. --> This micro service  saves profile informations such as Address and Phone number.

We developed the below apis .

POST /login (We send the username and password in the post body. User will be
authenticated and can perform the below operations.)
POST /profile ( Rest call between Authorization and Profile Service, user will be creating their
own profile info if they are authenticated. )
PUT /profile (Event based) (If user is authenticated they can update their own profile only. )
DELETE /profile (Event based) (If user is authenticated they can delete their own profile only. )

Both the microservices connects with each other through RestTemplate.
We use JWT(JSON Web token) for authorizing the user.
