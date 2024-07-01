#!/bin/bash

# Check if the correct number of arguments are provided
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 <email> <password>"
  exit 1
fi

# Assign the input arguments to variables
email=$1
password=$2

# Define the directory and file paths
config_dir="/etc/bazaar"
config_file="$config_dir/config.properties"

# Check if the directory exists, if not, create it
if [ ! -d "$config_dir" ]; then
  mkdir -p "$config_dir"
fi

# Create the config file with the desired contents
cat <<EOL > "$config_file"
email=$email
password=$password
EOL

# Set appropriate permissions for the file
chmod 600 "$config_file"

# Print a message indicating success
echo "Configuration file created at $config_file"

apt update
apt install -y fontconfig openjdk-17-jre git maven sudo curl wget

sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install -y jenkins
sudo apt-get install -y \
  wget \
  unzip \
  xvfb \
  libxi6 \
  libgconf-2-4 \
  libnss3 \
  libasound2 \
  libx11-xcb1 \
  libxcomposite1 \
  libxcursor1 \
  libxdamage1 \
  libxext6 \
  libxfixes3 \
  libxrandr2 \
  libxtst6 \
  fonts-liberation \
  libappindicator3-1 \
  xdg-utils \
  libatk-bridge2.0-0 \
  libgtk-3-0


wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo dpkg -i google-chrome-stable_current_amd64.deb
sudo apt-get -f install -y

echo "Jenkins installed. Initial Admin Password:"
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
