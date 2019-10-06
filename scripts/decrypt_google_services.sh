#!/bin/sh

# Decrypt the file
mkdir $HOME/secrets
# --batch to prevent interactive command --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="$FIREBASE_SECRET_PASSPHRASE" \
--output $HOME/secrets/google-services.json $PWD/app/google-services.json.gpg
