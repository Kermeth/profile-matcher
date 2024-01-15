
## Profile Matcher

### Description

Simple profile matcher that finds a profile from database and retrieves current campaign from api,
then matches the profile with the campaign and returns the result, adding the campaign to the profile if it matches.

### How to run

#### Requirements

- docker
- docker-compose

#### Steps

1. Clone the repository
2. Run `docker-compose up` in the root directory
3. Open `http://localhost:8080/get_client_config/{playerId}` in your browser
4. Replace `{playerId}` with a valid player id (97983be2-98b7-11e7-90cf-082e5f28d836)

### Campaign Matchers

There is an interface called `CampaignMatcher` that can be implemented to add new matchers for the profile.

### Docs

You can find API docs in `http://localhost:8080/swagger-ui.html` after running the application.